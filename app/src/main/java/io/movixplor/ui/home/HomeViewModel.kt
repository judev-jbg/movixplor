package io.movixplor.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.movixplor.domain.usecase.GetPopularMoviesUseCase
import io.movixplor.domain.usecase.SearchMoviesUseCase
import io.movixplor.util.NetworkError
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMoviesUseCase,
    private val searchMovies: SearchMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        observarBusqueda()
        cargarPeliculasPopulares()
    }

    private fun observarBusqueda() {
        viewModelScope.launch {
            _searchQuery
                .drop(1)           // ignora el valor inicial vacío del init
                .debounce(500)     // espera 500ms de inactividad antes de continuar
                .distinctUntilChanged()  // no dispara si el valor es igual al anterior
                .collectLatest { query ->  // cancela la búsqueda anterior si llega una nueva
                    if (query.isBlank()) {
                        cargarPeliculasPopulares()
                    } else {
                        buscarPeliculas(query)
                    }
                }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query  // actualiza el Flow, observarBusqueda reacciona
    }

    fun cargarPeliculasPopulares() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val peliculas = getPopularMovies()
                _uiState.value = if (peliculas.isEmpty()) {
                    HomeUiState.Empty("No hay películas disponibles")
                } else {
                    HomeUiState.Success(peliculas)
                }
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(NetworkError.parse(e))
            }
        }
    }

    private fun buscarPeliculas(query: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val resultados = searchMovies(query)
                _uiState.value = if (resultados.isEmpty()) {
                    HomeUiState.Empty("No se encontraron resultados para \"$query\"")
                } else {
                    HomeUiState.Success(resultados)
                }
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(NetworkError.parse(e))
            }
        }
    }
}