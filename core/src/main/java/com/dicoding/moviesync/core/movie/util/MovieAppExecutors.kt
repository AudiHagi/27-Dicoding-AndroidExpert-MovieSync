package com.dicoding.moviesync.core.movie.util

import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MovieAppExecutors @VisibleForTesting constructor(
    private val diskIO: Executor
) {

    companion object;

    constructor() : this(
        Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

}