package com.dicoding.moviesync.ui.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.moviesync.core.movie.domain.usecase.MovieUseCase

class TrendingViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getTrendingMovie().asLiveData()
}