package com.example.movieappmad24.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.movieappmad24.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    val movie = movieId?.let { Movie.getMovieById(it) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(movie?.title ?: "") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("homescreen") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go to Home-screen"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Card (modifier = Modifier.background(Color.Green)) { movie?.let { MovieRow(movie = movie, backgroundColor = Color.Cyan) } }
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = ShapeDefaults.Large,
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                LazyRow {
                    items(movie?.images ?: listOf()) { imageUrl ->
                        SubcomposeAsyncImage(
                            modifier = Modifier.padding(end = 16.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(imageUrl)
                                .crossfade(true)
                                .build(),
                            contentScale = ContentScale.Crop,
                            contentDescription = "movie poster",
                            loading = {
                                CircularProgressIndicator()
                            }
                        )
                    }
                }
            }
        }
    }
}