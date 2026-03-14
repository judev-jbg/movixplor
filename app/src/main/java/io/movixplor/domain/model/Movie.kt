package io.movixplor.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String?,   // ya es una URL completa, no solo el path
    val rating: Double,
    val releaseDate: String,
    val releaseYear: String   // campo derivado — conveniente para la UI
)