package com.example.movieappmad24.persistance

import com.example.movieappmad24.data.MovieDao
import com.example.movieappmad24.data.MovieImageDao
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class MovieRepository(private val movieDao: MovieDao, private val movieImageDao: MovieImageDao) {

    companion object {
        @Volatile
        private var Instance: MovieRepository? = null
        fun getInstance(movieDao: MovieDao, movieImageDao: MovieImageDao): MovieRepository {
            return Instance ?: synchronized(this) {
                Instance ?: MovieRepository(movieDao, movieImageDao).also { Instance = it }
            }
        }
    }

    suspend fun addMovie(movie: Movie) {
        movieDao.add(movie)
    }

    suspend fun updateMovie(movie: Movie) {
        movieDao.update(movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        movieDao.delete(movie)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAllMovies(): Flow<List<MovieWithImages>> {
        return movieDao.getAll().flatMapConcat { movies ->
            flow {
                val movieList = mutableListOf<MovieWithImages>()
                for (movie in movies) {
                    val imageUrls = movieImageDao.getUrlsById(movie.id).firstOrNull() ?: emptyList()
                    movieList.add(MovieWithImages(movie, imageUrls))
                }
                emit(movieList)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getFavoriteMovies(): Flow<List<MovieWithImages>> {
        return movieDao.getAllFavorites().flatMapConcat { movies ->
            flow {
                val movieList = mutableListOf<MovieWithImages>()
                for (movie in movies) {
                    val imageUrls = movieImageDao.getUrlsById(movie.id).firstOrNull() ?: emptyList()
                    movieList.add(MovieWithImages(movie, imageUrls))
                }
                emit(movieList)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMovieById(id: String): Flow<MovieWithImages?> {
        return movieDao.getMovieById(id).flatMapConcat { movie ->
            movieImageDao.getUrlsById(movie.id).map { imageUrls ->
                MovieWithImages(movie, imageUrls)
            }
        }
    }
}