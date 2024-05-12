package com.example.movieappmad24.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.movieappmad24.models.Movie
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {

    @Insert
    suspend fun add(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getAllFavorites(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMovieById(movieId: String): Flow<Movie>

    @Query("DELETE FROM movie")
    fun deleteAll()
}