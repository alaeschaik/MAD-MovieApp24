package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.movieappmad24.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(movieId: String?) {
    val movie = movieId?.let { Movie.getMovieById(it) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { movie?.title?.let { Text(it) } },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Card {
                movie?.let { MovieRow(movie = it) }
            }
            Card (
                modifier = Modifier
                    .fillMaxWidth(),
                shape = ShapeDefaults.Large,
                elevation = CardDefaults.cardElevation(10.dp)
            ){
                LazyRow {
                    movie?.let { movie ->
                        items(movie.images) { imageUrl ->
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
}