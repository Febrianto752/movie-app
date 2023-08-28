package com.example.movieapp.data.apis

import com.example.movieapp.data.models.responses.MovieDetailsResponseApi
import com.example.movieapp.data.models.responses.MoviesResponseApi
import com.example.movieapp.data.models.responses.UpComingMoviesResponseApi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MovieApiService {
    @GET("movie/top_rated?language=en-US&page=1")
    fun getTopRatedMovies(@Header("Authorization") authorization: String): Call<MoviesResponseApi>?

    @GET("movie/popular?language=en-US&page=1")
    fun getPopularMovies(@Header("Authorization") authorization: String): Call<MoviesResponseApi>?

    @GET("movie/upcoming?language=en-US&page=1")
    fun getUpComingMovies(@Header("Authorization") authorization: String): Call<UpComingMoviesResponseApi>?

    @GET("movie/{movie_id}?language=en-US")
    fun getMovieDetail(@Header("Authorization") authorization: String, @Path("movie_id") movieId: Int): Call<MovieDetailsResponseApi>?
}


var retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(GsonConverterFactory.create())

    .build()

object CONFIG {
    val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NzA1Y2Y5NjJjNTdkNzIyNjk0NDJhMGE5ZDFjZTY0MSIsInN1YiI6IjYxNmZjMWY4ZGY4NmE4MDA2NjZmNjc4NyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.PY31zhWOVzEHmCEPwvVUApv8mobA1fr0jQkaHEWLtyc"

    val URL_IMG = "https://image.tmdb.org/t/p/original"
}

