package com.example.movieappmad24.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    val id: String,
    val title: String,
    val year: String,
    val genre: String,
    val director: String,
    val actors: String,
    val plot: String,
    val trailer: String,
    val rating: String,
    var isFavorite: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}