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
                println("Start ========")
                var topRatedMoviesRequest = withContext(Dispatchers.IO) {
                    movieApiService.getTopRatedMovies(token)
                }

                var popularMoviesRequest = withContext(Dispatchers.IO) {
                    movieApiService.getPopularMovies(token)
                }

                var upComingMoviesRequest = withContext(Dispatchers.IO) {
                    movieApiService.getUpComingMovies(token)
                }
                println("top rated get !!!")
                if (topRatedMoviesRequest != null && popularMoviesRequest != null && upComingMoviesRequest != null) {
                    val responseTopRated = topRatedMoviesRequest.execute()
                    val responsePopular = popularMoviesRequest.execute()
                    val responseUpComing = upComingMoviesRequest.execute()


                    val topRatedMovieResponse = responseTopRated.body()
                    val topRatedResponseCode = responseTopRated.code()

                    val popularMovieResponse = responsePopular.body()
                    val popularResponseCode = responsePopular.code()

                    val upComingMovieResponse = responseUpComing.body()
                    val upComingResponseCode = responseUpComing.code()

                    println(topRatedResponseCode)

                    if ((topRatedResponseCode == 200 && topRatedMovieResponse != null) && (popularResponseCode == 200 && popularMovieResponse != null) && (upComingResponseCode == 200 && upComingMovieResponse != null)) {
                        topRatedMovies = topRatedMovieResponse.results;
                        popularMovies = popularMovieResponse.results;
                        upComingMovies = upComingMovieResponse.results;
                    }


                    println("hello1")
                }
            } catch (exception: Exception) {

                println("error : ${exception.message}")

            }

        }
    }

}