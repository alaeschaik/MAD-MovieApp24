package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.persistance.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Inherit from ViewModel class
class HomeScreenMovieViewModel(override val movieRepository: MovieRepository) : ViewModel(), IFavoriteMovieToggleViewModel {
    private val _movies =
        MutableStateFlow(listOf<MovieWithImages>())
    val movies: StateFlow<List<MovieWithImages>> = _movies

    init {
        viewModelScope.launch {
            movieRepository.getAllMovies().collect { movieList ->
                _movies.value = movieList
            }
        }
    }
}