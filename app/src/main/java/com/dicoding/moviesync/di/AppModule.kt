package com.dicoding.moviesync.di

import com.dicoding.moviesync.core.domain.usecase.MovieInteractor
import com.dicoding.moviesync.core.domain.usecase.MovieUseCase
import com.dicoding.moviesync.ui.current.CurrentViewModel
import com.dicoding.moviesync.ui.detail.DetailViewModel
import com.dicoding.moviesync.ui.favorite.FavoriteViewModel
import com.dicoding.moviesync.ui.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { CurrentViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}