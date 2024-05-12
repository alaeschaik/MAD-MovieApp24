package com.example.movieappmad24.viewmodels

import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.persistance.MovieRepository

interface IFavoriteMovieToggleViewModel {

    val movieRepository: MovieRepository
    suspend fun toggleFavoriteMovie(movieWithImages: MovieWithImages) {
        val newFavoriteState = !movieWithImages.movie.isFavorite
        movieWithImages.movie.isFavorite = newFavoriteState
        movieRepository.updateMovie(movieWithImages.movie)
    }
}