package com.dicoding.moviesync.core.movie.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("original_title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("release_date")
    val date: String,

    @field:SerializedName("vote_average")
    val voteAvg: Double,

    @field:SerializedName("vote_count")
    val voteCount: Int

)