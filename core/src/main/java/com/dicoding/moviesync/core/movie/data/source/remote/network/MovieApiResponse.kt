package com.dicoding.moviesync.core.movie.data.source.remote.network

sealed class MovieApiResponse<out R> {

    data class Success<out T>(val data: T) : MovieApiResponse<T>()

    data class Error(val errorMessage: String) : MovieApiResponse<Nothing>()

    object Empty : MovieApiResponse<Nothing>()

}