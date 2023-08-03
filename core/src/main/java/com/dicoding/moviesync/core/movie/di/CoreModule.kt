package com.dicoding.moviesync.core.movie.di

import androidx.room.Room
import com.dicoding.moviesync.core.movie.data.source.local.room.MovieDatabase
import com.dicoding.moviesync.core.movie.data.source.remote.MovieRemoteDataSource
import com.dicoding.moviesync.core.movie.data.source.remote.network.MovieApiService
import com.dicoding.moviesync.core.movie.domain.repository.IMovieRepository
import com.dicoding.moviesync.core.movie.util.MovieAppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.dicoding.moviesync.core.BuildConfig as config

val movieDatabaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("moviesync".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(), MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val movieNetworkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/NPIMWkzcNG/MyZsVExrC6tdy5LTZzeeKg2UlnGG55UY=")
            .add(hostname, "sha256/DxH4tt40L+eduF6szpY6TONlxhZhBd+pJ9wbHlQ2fuw=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()
        val loggingInterceptor = if (config.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val token = config.API_KEY
                val requestWithAuthorization = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                chain.proceed(requestWithAuthorization)
            }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()

        client
    }
    single {
        val retrofit =
            Retrofit.Builder().baseUrl(config.BASE_URL)
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