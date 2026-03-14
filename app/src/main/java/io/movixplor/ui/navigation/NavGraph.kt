package io.movixplor.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.movixplor.ui.detail.DetailScreen
import io.movixplor.ui.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route  // pantalla inicial
    ) {

        composable(route = Routes.Home.route) {
            HomeScreen(
                onMovieClick = { movieId ->
                    // navega a detail pasando el id en la ruta
                    navController.navigate(Routes.Detail.createRoute(movieId))
                }
            )
        }

        composable(
            route = Routes.Detail.route,
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            // extrae el argumento del back stack
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable
            DetailScreen(
                movieId = movieId,
                onBack = { navController.popBackStack() }  // vuelve a Home
            )
        }
    }
}