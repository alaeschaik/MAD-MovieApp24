package com.example.movieappmad24.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.movieappmad24.dependencyInjection.Injector
import com.example.movieappmad24.viewmodels.HomeScreenMovieViewModel
import com.example.movieappmad24.widgets.MovieList
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val homeScreenViewModel: HomeScreenMovieViewModel = viewModel(
        factory = Injector.provideViewModelFactory(
            LocalContext.current
        )
    )
    Scaffold(
        topBar = {
            SimpleTopAppBar(title = "Movie App")
        },
        bottomBar = {
            SimpleBottomAppBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        MovieList(
            innerPadding = innerPadding,
            moviesWithImages = homeScreenViewModel.movies.collectAsState().value,
            navController = navController,
            viewModel = homeScreenViewModel
        )
    }
}

