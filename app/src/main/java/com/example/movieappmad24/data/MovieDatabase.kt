package com.example.movieappmad24.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieImage
import com.example.movieappmad24.models.getMoviesWithImages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Movie::class, MovieImage::class],
    version = 1,
    exportSchema = false
)

abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieImageDao(): MovieImageDao
    companion object{
        @Volatile
        private var Instance: MovieDatabase? = null
        fun getDatabase(context: Context): MovieDatabase{
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback(context.applicationContext))
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }

    private class DatabaseCallback(private val context: Context) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                // Populate the database with initial data in the background
                val movieDao = getDatabase(context).movieDao()
                val movieImageDao = getDatabase(context).movieImageDao()
                val moviesWithImages = getMoviesWithImages()

                for (movieWithImages in moviesWithImages) {
                    movieDao.add(movieWithImages.movie)
                    for (url in movieWithImages.imageUrls) {
                        val movieImage = MovieImage(movieId = movieWithImages.movie.id, url = url)
                        movieImageDao.addMovieImage(movieImage)
                    }
                }
            }
        }
    }
}