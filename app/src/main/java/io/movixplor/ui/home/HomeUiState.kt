package io.movixplor.ui.home

import io.movixplor.domain.model.Movie

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val movies: List<Movie>) : HomeUiState()
    data class Empty(val message: String) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}