package com.dicoding.moviesync.core.movie.data

import com.dicoding.moviesync.core.movie.data.source.remote.network.MovieApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class MovieNetworkBoundResources<ResultType, RequestType> {

    private var result: Flow<MovieResource<ResultType>> = flow {
        emit(MovieResource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(MovieResource.Loading())
            when (val apiResponse = createCall().first()) {
                is MovieApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        MovieResource.Success(it)
                    })
                }

                is MovieApiResponse.Empty -> {
                    emitAll(loadFromDB().map {
                        MovieResource.Success(it)
                    })
                }

                is MovieApiResponse.Error -> {
                    onFetchFailed()
                    emit(
                        MovieResource.Error<ResultType>(
                            apiResponse.errorMessage
                        )
                    )
                }
            }
        } else {
            emitAll(loadFromDB().map {
                MovieResource.Success(it)
            })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<MovieApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<MovieResource<ResultType>> = result

}