package com.example.movieapp.data.models

data class Movie(
    var id: Int,
    var adult: Boolean,
    var backdrop_path: String,
    var genre_ids: List<Int>,
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

fun Movie.toMovieFavorite(): MovieFavorite {
    var movieFavorite = MovieFavorite(
        id = id,
        adult = adult,
        backdrop_path = backdrop_path,
        genre_ids = genre_ids.joinToString(", "),
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

    return movieFavorite;
}
