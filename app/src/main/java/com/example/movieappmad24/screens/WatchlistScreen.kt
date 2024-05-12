package com.example.movieappmad24.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.movieappmad24.dependencyInjection.Injector
import com.example.movieappmad24.viewmodels.WatchListMovieViewModel
import com.example.movieappmad24.widgets.MovieList
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar

@Composable
fun WatchlistScreen(
    navController: NavHostController
) {
    val watchListViewModel: WatchListMovieViewModel =
        viewModel(factory = Injector.provideViewModelFactory(LocalContext.current))
    Scaffold(
        topBar = {
            SimpleTopAppBar(title = "Your Watchlist")
        },
        bottomBar = {
            SimpleBottomAppBar(
                navController = navController
            )
        }
    ) { innerPadding ->

        MovieList(
            innerPadding = innerPadding,
            moviesWithImages = watchListViewModel.movies.collectAsState().value,
            navController = navController,
            viewModel = watchListViewModel
        )

    }
}