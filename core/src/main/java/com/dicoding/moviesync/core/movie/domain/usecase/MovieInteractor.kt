package com.dicoding.moviesync.core.movie.domain.usecase

import com.dicoding.moviesync.core.movie.domain.model.Movie
import com.dicoding.moviesync.core.movie.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getTrendingMovie() = movieRepository.getTrendingMovie()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

}