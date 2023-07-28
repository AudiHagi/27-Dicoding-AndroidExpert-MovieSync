package com.dicoding.moviesync.utils

import com.dicoding.moviesync.core.data.source.local.entity.MovieEntity
import com.dicoding.moviesync.core.data.source.remote.response.MovieResponse
import com.dicoding.moviesync.core.domain.model.Movie

object DataMapper {

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