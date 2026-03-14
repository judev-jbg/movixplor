package io.movixplor.domain.repository

import io.movixplor.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): List<Movie>
    suspend fun getTopRatedMovies(): List<Movie>
    suspend fun searchMovies(query: String): List<Movie>
    suspend fun getMovieDetail(movieId: Int): Movie
}
