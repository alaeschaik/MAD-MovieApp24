package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.persistance.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DetailScreenViewModel(override val movieRepository: MovieRepository) : ViewModel(), IFavoriteMovieToggleViewModel {

    fun getMovieById(movieId: String): Flow<MovieWithImages?> {
        var movie: Flow<MovieWithImages?>? = null
        viewModelScope.launch {
            movie = movieRepository.getMovieById(movieId)
        }
        return movie ?: throw IllegalArgumentException("Movie not found")
    }
}