package com.dicoding.moviesync.core.movie.util

import com.dicoding.moviesync.core.movie.data.source.local.entity.TrendingEntity
import com.dicoding.moviesync.core.movie.data.source.remote.response.TrendingResponse
import com.dicoding.moviesync.core.movie.domain.model.Movie

object TrendingDataMapper {

    fun mapResponsesToEntities(input: List<TrendingResponse>): List<TrendingEntity> {
        val movieList = ArrayList<TrendingEntity>()
        input.map {
            val movie = TrendingEntity(
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

    fun mapEntitiesToDomain(input: List<TrendingEntity>): List<Movie> =
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

}