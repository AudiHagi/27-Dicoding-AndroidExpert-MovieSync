package com.dicoding.moviesync.core.movie.data.source.remote.network

import com.dicoding.moviesync.core.movie.data.source.remote.response.ListMovieResponse
import com.dicoding.moviesync.core.movie.data.source.remote.response.ListTrendingResponse
import retrofit2.http.GET

interface MovieApiService {

    @GET("3/discover/movie")
    suspend fun getList(): ListMovieResponse

    @GET("3/trending/movie/day")
    suspend fun getListTrending(): ListTrendingResponse

}