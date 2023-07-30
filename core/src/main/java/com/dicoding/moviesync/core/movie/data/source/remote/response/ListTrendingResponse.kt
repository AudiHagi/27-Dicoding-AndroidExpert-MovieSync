package com.dicoding.moviesync.core.movie.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTrendingResponse(

    @field:SerializedName("results")
    val results: List<TrendingResponse>

)