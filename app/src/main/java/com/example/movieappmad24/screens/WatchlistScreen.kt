package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.model.Movie
import com.example.movieappmad24.widgets.SimpleBottomBar
import com.example.movieappmad24.widgets.SimpleTopBar

@Composable
fun WatchlistScreen (navController: NavController){
    Scaffold(
        topBar = { SimpleTopBar("Your Watchlist") },
        bottomBar = { SimpleBottomBar(navController = navController) }
    ) { innerPadding ->
        MovieList(
            modifier = Modifier.padding(innerPadding),
            movies = Movie.getMovies().take(5),
            navController = navController
        )
    }
}