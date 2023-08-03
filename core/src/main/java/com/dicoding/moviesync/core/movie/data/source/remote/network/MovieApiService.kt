package com.dicoding.moviesync.core.movie.data.source.remote.network

import com.dicoding.moviesync.core.movie.data.source.remote.response.ListMovieResponse
import com.dicoding.moviesync.core.movie.data.source.remote.response.ListTrendingResponse
import retrofit2.http.GET

interface MovieApiService {

    @GET("3/movie/now_playing?language=en-US&page=1")
    suspend fun getList(): ListMovieResponse

    @GET("3/trending/movie/day")
    suspend fun getListTrending(): ListTrendingResponse

}