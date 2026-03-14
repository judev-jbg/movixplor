package io.movixplor.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path")    // la API usa snake_case, Kotlin usa camelCase
    val posterPath: String?,       // nullable — no todas las películas tienen poster
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("release_date")
    val releaseDate: String
)

@Serializable
data class MovieListResponse(
    val page: Int,
    val results: List<MovieDto>,
    @SerialName("total_pages")
    val totalPages: Int
)