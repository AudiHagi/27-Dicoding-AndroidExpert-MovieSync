package com.dicoding.moviesync.core.movie.data.source.local

import com.dicoding.moviesync.core.movie.data.source.local.entity.MovieEntity
import com.dicoding.moviesync.core.movie.data.source.local.entity.TrendingEntity
import com.dicoding.moviesync.core.movie.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getTrendingMovie(): Flow<List<TrendingEntity>> = movieDao.getTrendingMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    suspend fun insertTrendingMovie(movieList: List<TrendingEntity>) =
        movieDao.insertTrendingMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

}