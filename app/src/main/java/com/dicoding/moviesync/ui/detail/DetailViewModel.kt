package com.dicoding.moviesync.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.moviesync.core.domain.model.Movie
import com.dicoding.moviesync.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}