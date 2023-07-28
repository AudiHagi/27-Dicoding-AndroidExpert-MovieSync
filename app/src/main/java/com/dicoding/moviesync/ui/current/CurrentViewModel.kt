package com.dicoding.moviesync.ui.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.moviesync.core.domain.usecase.MovieUseCase

class CurrentViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val tourism = movieUseCase.getAllMovie().asLiveData()
}