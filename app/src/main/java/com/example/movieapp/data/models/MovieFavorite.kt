package com.example.movieapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_favorites")
data class MovieFavorite(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var movie_id: Int,
    var adult: Boolean,
    var backdrop_path: String,
    var genre_ids: String,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var release_date: String,
    var title: String,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int
)