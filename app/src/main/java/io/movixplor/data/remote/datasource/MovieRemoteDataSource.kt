package io.movixplor.data.remote.datasource

import io.movixplor.BuildConfig
import io.movixplor.data.remote.api.TmdbApi
import io.movixplor.data.remote.dto.MovieDto
import io.movixplor.data.remote.dto.MovieListResponse
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val api: TmdbApi
) {
    suspend fun getPopularMovies(page: Int = 1): MovieListResponse {
        return api.getPopularMovies(
            apiKey = BuildConfig.TMDB_API_KEY,
            page = page
        )
    }

    suspend fun getTopRatedMovies(page: Int = 1): MovieListResponse {
        return api.getTopRatedMovies(
            apiKey = BuildConfig.TMDB_API_KEY,
            page = page
        )
    }

    suspend fun searchMovies(query: String, page: Int = 1): MovieListResponse {
        return api.searchMovies(
            apiKey = BuildConfig.TMDB_API_KEY,
            query = query,
            page = page
        )
    }

    suspend fun getMovieDetail(movieId: Int): MovieDto {
        return api.getMovieDetail(
            movieId = movieId,
            apiKey = BuildConfig.TMDB_API_KEY
        )
    }
}