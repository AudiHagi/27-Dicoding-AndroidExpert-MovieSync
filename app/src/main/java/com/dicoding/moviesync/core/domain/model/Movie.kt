package com.dicoding.moviesync.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    val movieId: String,

    val title: String,

    val overview: String,

    val poster: String,

    val date: String,

    val voteAvg: Double,

    val voteCount: Int,

    val isFavorite: Boolean

) : Parcelable