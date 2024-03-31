package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.navigation.Screens.Companion.DetailScreenDestination
import com.example.movieappmad24.navigation.Screens.Companion.HomeScreenDestination
import com.example.movieappmad24.navigation.Screens.Companion.WatchlistScreenDestination
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.HomeScreen
import com.example.movieappmad24.screens.WatchlistScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination.route
    ) {

        composable(route = HomeScreenDestination.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = DetailScreenDestination.routeWithArgument,
            arguments = listOf(navArgument(name = DetailScreenDestination.argument) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getString(DetailScreenDestination.argument)
            )
        }

        composable(WatchlistScreenDestination.route) {
            WatchlistScreen(navController = navController)
        }
    }
}