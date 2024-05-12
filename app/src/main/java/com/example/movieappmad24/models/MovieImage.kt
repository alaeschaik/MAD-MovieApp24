package com.example.movieappmad24.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Movie::class,
        parentColumns = ["id"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["movieId"])]
)
data class MovieImage(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val movieId: String,
    val url: String
)