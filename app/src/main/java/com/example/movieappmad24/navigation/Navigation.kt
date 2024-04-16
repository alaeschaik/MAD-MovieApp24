package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.HomeScreen
import com.example.movieappmad24.screens.WatchlistScreen
import com.example.movieappmad24.viewmodels.MoviesViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController() // create a NavController instance
    val moviesViewModel: MoviesViewModel = viewModel()

    NavHost(
        navController = navController, // pass the NavController to NavHost
        startDestination = Screen.HomeScreen.route
    ) {  // pass a start destination
        composable(route = Screen.HomeScreen.route) {   // route with name "homescreen" navigates to HomeScreen composable
            HomeScreen(navController = navController, viewModel = moviesViewModel)
        }

        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                viewModel = moviesViewModel,
                movieId = backStackEntry.arguments?.getString(
                    DETAIL_ARGUMENT_KEY
                )
            )
        }

        composable(route = Screen.WatchlistScreen.route) {
            WatchlistScreen(navController = navController, viewModel = moviesViewModel)
        }
    }
}