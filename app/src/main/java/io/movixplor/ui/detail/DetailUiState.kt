package io.movixplor.ui.detail

import io.movixplor.domain.model.Movie

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val movie: Movie) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}