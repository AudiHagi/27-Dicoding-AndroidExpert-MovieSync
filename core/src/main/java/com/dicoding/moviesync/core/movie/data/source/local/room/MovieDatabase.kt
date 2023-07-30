package com.dicoding.moviesync.core.movie.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.moviesync.core.movie.data.source.local.entity.MovieEntity
import com.dicoding.moviesync.core.movie.data.source.local.entity.TrendingEntity

@Database(entities = [MovieEntity::class, TrendingEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}