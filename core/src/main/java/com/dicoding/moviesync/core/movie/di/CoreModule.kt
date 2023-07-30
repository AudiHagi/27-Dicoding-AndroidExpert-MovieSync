package com.dicoding.moviesync.core.movie.di

import androidx.room.Room
import com.dicoding.moviesync.core.movie.data.source.local.room.MovieDatabase
import com.dicoding.moviesync.core.movie.data.source.remote.MovieRemoteDataSource
import com.dicoding.moviesync.core.movie.data.source.remote.network.MovieApiService
import com.dicoding.moviesync.core.movie.domain.repository.IMovieRepository
import com.dicoding.moviesync.core.movie.util.MovieAppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val movieDatabaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(), MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val movieNetworkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val token =
                    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODNjYmFkYTlkM2I4MGE5MjAzZTkyODg5MTlhYjRjMiIsInN1YiI6IjY0YzI1MTc0ZGI0ZWQ2MDBlNGNhY2M3ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.uzA6SlTIP8Xrt6tEDUjdi0wktRwHHI6rRnt1lb4W9II"
                val requestWithAuthorization = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                chain.proceed(requestWithAuthorization)
            }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit =
            Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
        retrofit.create(MovieApiService::class.java)
    }
}

val movieRepositoryModule = module {
    single {
        com.dicoding.moviesync.core.movie.data.source.local.MovieLocalDataSource(get())
    }
    single {
        MovieRemoteDataSource(get())
    }
    factory {
        MovieAppExecutors()
    }
    single<IMovieRepository> {
        com.dicoding.moviesync.core.movie.data.MovieRepository(
            get(), get(), get()
        )
    }

}