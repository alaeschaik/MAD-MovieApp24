package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.persistance.MovieRepository


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailScreenViewModel::class.java) -> {
                DetailScreenViewModel(movieRepository = repository) as T
            }

            modelClass.isAssignableFrom(HomeScreenMovieViewModel::class.java) -> {
                HomeScreenMovieViewModel(movieRepository = repository) as T
            }

            modelClass.isAssignableFrom(WatchListMovieViewModel::class.java) -> {
                WatchListMovieViewModel(movieRepository = repository) as T
            }

            else -> throw IllegalArgumentException("Unknown VieModel class")
        }
    }
}