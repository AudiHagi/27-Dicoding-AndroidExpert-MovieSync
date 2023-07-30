package com.dicoding.moviesync.core.movie.util

import com.dicoding.moviesync.core.movie.data.source.local.entity.MovieEntity
import com.dicoding.moviesync.core.movie.data.source.remote.response.MovieResponse
import com.dicoding.moviesync.core.movie.domain.model.Movie

object MovieDataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                poster = it.poster,
                date = it.date,
                voteAvg = it.voteAvg,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                poster = it.poster,
                date = it.date,
                voteAvg = it.voteAvg,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        overview = input.overview,
        poster = input.poster,
        date = input.date,
        voteAvg = input.voteAvg,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )

}