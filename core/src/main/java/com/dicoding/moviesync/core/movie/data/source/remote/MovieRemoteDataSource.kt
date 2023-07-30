package com.dicoding.moviesync.core.movie.data.source.remote

import android.util.Log
import com.dicoding.moviesync.core.movie.data.source.remote.network.MovieApiResponse
import com.dicoding.moviesync.core.movie.data.source.remote.network.MovieApiService
import com.dicoding.moviesync.core.movie.data.source.remote.response.MovieResponse
import com.dicoding.moviesync.core.movie.data.source.remote.response.TrendingResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRemoteDataSource(private val movieApiService: MovieApiService) {

    suspend fun getAllMovie(): Flow<MovieApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = movieApiService.getList()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(MovieApiResponse.Success(response.results))
                } else {
                    emit(MovieApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(MovieApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource : ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTrending(): Flow<MovieApiResponse<List<TrendingResponse>>> {
        return flow {
            try {
                val response = movieApiService.getListTrending()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(MovieApiResponse.Success(response.results))
                } else {
                    emit(MovieApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(MovieApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource : ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}