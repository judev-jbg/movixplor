package io.movixplor.domain.usecase

import io.movixplor.domain.model.Movie
import io.movixplor.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): List<Movie> {
        return repository.getPopularMovies()
    }
}