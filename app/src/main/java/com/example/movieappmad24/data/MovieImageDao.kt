package com.example.movieappmad24.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.movieappmad24.models.MovieImage
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieImageDao {
    @Insert
    suspend fun addMovieImage(movieImage: MovieImage)

    @Update
    suspend fun updateMovieImage(movieImage: MovieImage)

    @Delete
    suspend fun deleteMovieImage(movieImage: MovieImage)

    @Query("SELECT url FROM movieimage WHERE movieId = :movieId")
    fun getUrlsById(movieId: String): Flow<List<String>>

    @Query("SELECT url FROM movieimage")
    fun getAllUrls(): Flow<List<String>>
}