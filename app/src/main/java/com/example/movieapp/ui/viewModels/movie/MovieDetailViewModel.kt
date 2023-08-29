package com.example.movieapp.ui.viewModels.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.apis.CONFIG
import com.example.movieapp.data.apis.MovieApiService
import com.example.movieapp.data.models.Movie
import com.example.movieapp.data.models.responses.MovieDetailsResponseApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(private val movieApiService: MovieApiService):ViewModel() {
    private val token = "Bearer ${CONFIG.API_KEY}"
    var movieDetail by mutableStateOf<MovieDetailsResponseApi?>(null)
        private set

    fun setMovieDetail(movieId: Int){
        GlobalScope.launch(Dispatchers.IO) {

            try {

                var getMovieByRequest = withContext(Dispatchers.IO) {
                    movieApiService.getMovieDetail(token, movieId)
                }

                if (getMovieByRequest != null) {
                    val responseTopRated = getMovieByRequest.execute()


                    val movieByIdResponse = responseTopRated.body()
                    val movieByIdResponseCode = responseTopRated.code()


                    println(movieByIdResponseCode)

                    if (movieByIdResponseCode == 200 && movieByIdResponse != null) {
                        movieDetail = movieByIdResponse;
                    }


                    println("hello1")
                }
            } catch (exception: Exception) {

                println("error : ${exception.message}")

            }

        }
    }
}