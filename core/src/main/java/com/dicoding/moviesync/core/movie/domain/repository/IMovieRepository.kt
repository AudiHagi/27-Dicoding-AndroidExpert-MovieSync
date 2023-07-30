package com.dicoding.moviesync.core.movie.domain.repository

import com.dicoding.moviesync.core.movie.data.MovieResource
import com.dicoding.moviesync.core.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(): Flow<MovieResource<List<Movie>>>

    fun getTrendingMovie(): Flow<MovieResource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

}