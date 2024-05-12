package com.example.movieappmad24.dependencyInjection

import android.content.Context
import com.example.movieappmad24.data.MovieDatabase
import com.example.movieappmad24.persistance.MovieRepository
import com.example.movieappmad24.viewmodels.ViewModelFactory

object Injector {
    private fun getMovieRepository(context: Context): MovieRepository {
        val db = MovieDatabase.getDatabase(context.applicationContext)
        val movieDao = db.movieDao()
        val movieImageDao = db.movieImageDao()
        return MovieRepository(movieDao, movieImageDao)
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val movieRepository = getMovieRepository(context)
        return ViewModelFactory(movieRepository)
    }
}