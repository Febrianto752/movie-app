package com.example.movieapp.ui.viewModels.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.apis.CONFIG
import com.example.movieapp.data.apis.MovieApiService
import com.example.movieapp.data.database.tables.users.IUsersRepository
import com.example.movieapp.data.models.Movie
import com.example.movieapp.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await


class MovieViewModel(private val movieApiService: MovieApiService) : ViewModel() {
    private val token = "Bearer ${CONFIG.API_KEY}"
    var topRatedMovies by mutableStateOf<List<Movie>>(emptyList())
        private set

    var popularMovies by mutableStateOf<List<Movie>>(emptyList())
        private set

    var upComingMovies by mutableStateOf<List<Movie>>(emptyList())
        private set

    init {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                var topRatedMoviesRequest = movieApiService.getTopRatedMovies(token)
                var popularMoviesRequest = movieApiService.getPopularMovies(token)
                var upComingMoviesRequest = movieApiService.getUpComingMovies(token)


                if (topRatedMoviesRequest != null && popularMoviesRequest != null && upComingMoviesRequest != null) {
                    val topRatedResponse = topRatedMoviesRequest.execute()
                    val popularResponse = popularMoviesRequest.execute()
                    val upComingResponse = upComingMoviesRequest.execute()

                    val topRatedResponseBody = topRatedResponse.body()
                    val topRatedResponseCode = topRatedResponse.code()

                    val popularResponseBody = popularResponse.body()
                    val popularResponseCode = popularResponse.code()

                    val upComingResponseBody = upComingResponse.body()
                    val upComingResponseCode = upComingResponse.code()

                    println(topRatedResponseCode)

                    if ((topRatedResponseCode == 200 && topRatedResponseBody != null) && (popularResponseCode == 200 && popularResponseBody != null) && (upComingResponseCode == 200 && upComingResponseBody != null)) {
                        topRatedMovies = topRatedResponseBody.results;
                        popularMovies = popularResponseBody.results;
                        upComingMovies = upComingResponseBody.results;
                    }

                }
            } catch (exception: Exception) {

                println("error : ${exception.message}")

            }

        }
    }

}