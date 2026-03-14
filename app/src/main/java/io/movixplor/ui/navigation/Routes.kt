package io.movixplor.ui.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Detail : Routes("detail/{movieId}") {
        // función helper para construir la ruta con el id real
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}