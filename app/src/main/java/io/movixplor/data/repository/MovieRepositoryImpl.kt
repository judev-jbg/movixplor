package io.movixplor.data.repository

import io.movixplor.data.remote.datasource.MovieRemoteDataSource
import io.movixplor.data.remote.mapper.toDomain
import io.movixplor.domain.model.Movie
import io.movixplor.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return remoteDataSource
            .getPopularMovies()
            .results
            .map { it.toDomain() }  // convierte cada MovieDto a Movie
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        return remoteDataSource
            .getTopRatedMovies()
            .results
            .map { it.toDomain() }
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        return remoteDataSource
            .searchMovies(query)
            .results
            .map { it.toDomain() }
    }

    override suspend fun getMovieDetail(movieId: Int): Movie {
        return remoteDataSource.getMovieDetail(movieId).toDomain()
    }
}
