package com.dicoding.moviesync.di

import com.dicoding.moviesync.core.movie.domain.usecase.MovieInteractor
import com.dicoding.moviesync.core.movie.domain.usecase.MovieUseCase
import com.dicoding.moviesync.ui.detail.DetailViewModel
import com.dicoding.moviesync.ui.favorite.FavoriteViewModel
import com.dicoding.moviesync.ui.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieUseCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val movieViewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}