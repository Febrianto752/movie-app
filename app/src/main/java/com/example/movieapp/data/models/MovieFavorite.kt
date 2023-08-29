package com.example.movieapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_favorites")
data class MovieFavorite(
    @PrimaryKey(autoGenerate = true)
    var pk: Int = 0,
    var id: Int,
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
    var vote_count: Int,
    var user_id: Int = 0
)

fun MovieFavorite.toMovie(): Movie{
    var movie = Movie(
        id = id,
        adult = adult,
        backdrop_path = backdrop_path,
        genre_ids = genre_ids.split(", ").map { it.toInt() },
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        release_date,
        title,
        video,
        vote_average,
        vote_count
    )

    return movie;
}