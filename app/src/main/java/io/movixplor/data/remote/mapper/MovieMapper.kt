package io.movixplor.data.remote.mapper

import io.movixplor.data.remote.dto.MovieDto
import io.movixplor.domain.model.Movie

// URL base que TMDB usa para construir la URL completa del poster
private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterPath?.let { "$IMAGE_BASE_URL$it" },  // si existe, construye URL completa
        rating = voteAverage,
        releaseDate = releaseDate,
        releaseYear = releaseDate.take(4)  // "2024-03-15" → "2024"
    )
}