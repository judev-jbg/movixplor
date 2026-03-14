package io.movixplor.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.movixplor.domain.usecase.GetMovieDetailUseCase
import io.movixplor.util.NetworkError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetail: GetMovieDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun cargarDetalle(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            try {
                val movie = getMovieDetail(movieId)
                _uiState.value = DetailUiState.Success(movie)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(
                    NetworkError.parse(e)
                )
            }
        }
    }
}