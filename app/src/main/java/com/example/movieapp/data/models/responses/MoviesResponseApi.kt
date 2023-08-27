package com.example.movieapp.data.models.responses

import com.example.movieapp.data.models.Movie

data class MoviesResponseApi (
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)