package com.dicoding.moviesync.core.data.source.remote.network

import com.dicoding.moviesync.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {

    @GET("3/discover/movie")
    suspend fun getList(): ListMovieResponse

}