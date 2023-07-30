package com.dicoding.moviesync.core.movie.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending")
data class TrendingEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "vote_avg")
    var voteAvg: Double,

    @ColumnInfo(name = "vote_count")
    var voteCount: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

)