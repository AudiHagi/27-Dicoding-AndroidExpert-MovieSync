package com.dicoding.moviesync.di

import com.dicoding.moviesync.ui.trending.TrendingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trendModule = module {
    viewModel { TrendingViewModel(get()) }
}