package com.dicoding.moviesync.core.movie.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dicoding.moviesync.core.movie.data.source.local.entity.MovieEntity
import com.dicoding.moviesync.core.movie.data.source.local.entity.TrendingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM trending")
    fun getTrendingMovie(): Flow<List<TrendingEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingMovie(movie: List<TrendingEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

}