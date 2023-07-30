package com.dicoding.moviesync.core.movie.data

import com.dicoding.moviesync.core.movie.data.source.local.MovieLocalDataSource
import com.dicoding.moviesync.core.movie.data.source.remote.MovieRemoteDataSource
import com.dicoding.moviesync.core.movie.data.source.remote.network.MovieApiResponse
import com.dicoding.moviesync.core.movie.data.source.remote.response.MovieResponse
import com.dicoding.moviesync.core.movie.data.source.remote.response.TrendingResponse
import com.dicoding.moviesync.core.movie.domain.model.Movie
import com.dicoding.moviesync.core.movie.domain.repository.IMovieRepository
import com.dicoding.moviesync.core.movie.util.MovieAppExecutors
import com.dicoding.moviesync.core.movie.util.MovieDataMapper
import com.dicoding.moviesync.core.movie.util.TrendingDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieAppExecutors: MovieAppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<MovieResource<List<Movie>>> =
        object :
            MovieNetworkBoundResources<List<Movie>, List<MovieResponse>>() {

            override fun loadFromDB(): Flow<List<Movie>> {
                return movieLocalDataSource.getAllMovie()
                    .map { MovieDataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<MovieApiResponse<List<MovieResponse>>> =
                movieRemoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = MovieDataMapper.mapResponsesToEntities(data)
                movieLocalDataSource.insertMovie(movieList)
            }

        }.asFlow()

    override fun getTrendingMovie(): Flow<MovieResource<List<Movie>>> =
        object :
            MovieNetworkBoundResources<List<Movie>, List<TrendingResponse>>() {

            override fun loadFromDB(): Flow<List<Movie>> {
                return movieLocalDataSource.getTrendingMovie()
                    .map { TrendingDataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<MovieApiResponse<List<TrendingResponse>>> =
                movieRemoteDataSource.getAllTrending()

            override suspend fun saveCallResult(data: List<TrendingResponse>) {
                val movieList = TrendingDataMapper.mapResponsesToEntities(data)
                movieLocalDataSource.insertTrendingMovie(movieList)
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return movieLocalDataSource.getFavoriteMovie().map {
            MovieDataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = MovieDataMapper.mapDomainToEntity(movie)
        movieAppExecutors.diskIO().execute {
            movieLocalDataSource.setFavoriteMovie(movieEntity, state)
        }
    }

}