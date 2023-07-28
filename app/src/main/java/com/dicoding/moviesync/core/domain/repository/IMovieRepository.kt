package com.dicoding.moviesync.core.domain.repository

import com.dicoding.moviesync.core.data.Resource
import com.dicoding.moviesync.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

}