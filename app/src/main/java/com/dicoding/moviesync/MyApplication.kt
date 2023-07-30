package com.dicoding.moviesync

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.dicoding.moviesync.core.movie.di.movieDatabaseModule
import com.dicoding.moviesync.core.movie.di.movieNetworkModule
import com.dicoding.moviesync.core.movie.di.movieRepositoryModule
import com.dicoding.moviesync.di.movieUseCaseModule
import com.dicoding.moviesync.di.movieViewModelModule
import com.dicoding.moviesync.utils.DarkMode
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.Locale

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        preferences.getString(
            getString(R.string.pref_key_dark),
            getString(R.string.pref_dark_follow_system)
        )?.apply {
            val mode = DarkMode.valueOf(this.uppercase(Locale.US))
            AppCompatDelegate.setDefaultNightMode(mode.value)
        }

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    movieDatabaseModule,
                    movieNetworkModule,
                    movieRepositoryModule,
                    movieUseCaseModule,
                    movieViewModelModule
                )
            )
        }
    }

}