package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.model.Movie
import com.example.movieappmad24.model.Movie.Companion.getMovies
import com.example.movieappmad24.navigation.Screens.Companion.DetailScreenDestination
import com.example.movieappmad24.widgets.MovieRow
import com.example.movieappmad24.widgets.SimpleBottomBar
import com.example.movieappmad24.widgets.SimpleTopBar

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { SimpleTopBar(title = "Movie App") },
        bottomBar = { SimpleBottomBar(navController = navController) }
    ) { innerPadding ->
        MovieList(
            modifier = Modifier.padding(innerPadding),
            movies = getMovies(),
            navController = navController
        )
    }
}

@Composable
fun MovieList(
    modifier: Modifier,
    movies: List<Movie> = getMovies(),
    navController: NavController
) {
    LazyColumn(modifier = modifier) {
        items(movies) { movie ->
            MovieRow(movie = movie) { movieId ->
                navController.navigate(DetailScreenDestination.navigate(movieId))
            }
        }
    }
}


