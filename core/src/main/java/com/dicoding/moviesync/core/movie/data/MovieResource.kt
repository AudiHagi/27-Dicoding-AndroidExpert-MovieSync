package com.dicoding.moviesync.core.movie.data

sealed class MovieResource<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T?) : MovieResource<T>(data)

    class Loading<T>(data: T? = null) : MovieResource<T>(data)

    class Error<T>(message: String?, data: T? = null) :
        MovieResource<T>(data, message)

}