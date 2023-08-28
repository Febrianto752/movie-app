package com.example.movieapp.ui.viewModels.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.database.tables.movie_favorites.IMovieFavoritesRepository
import com.example.movieapp.data.database.tables.users.IUsersRepository
import com.example.movieapp.data.models.MovieFavorite
import com.example.movieapp.data.models.User
import kotlinx.coroutines.launch

class MovieFavoriteViewModel(private val movieFavoritesRepository: IMovieFavoritesRepository): ViewModel() {
    var movieFavoriteList by mutableStateOf<List<MovieFavorite>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            var movieFavorites = movieFavoritesRepository.getAllMovieFavoritesStream()
            movieFavorites.collect { movieFavoritesParam ->
                movieFavoriteList = movieFavoritesParam;
            }
        }
    }

    suspend fun updateMovieFavoriteList(){
        var movieFavorites = movieFavoritesRepository.getAllMovieFavoritesStream()
        movieFavorites.collect{movieFavoriteListParam ->
            movieFavoriteList = movieFavoriteListParam;
        }
    }

    suspend fun createMovieFavorite(movieFavorite: MovieFavorite) {
        movieFavoritesRepository.insertMovieFavorite(movieFavorite)
        updateMovieFavoriteList()
    }

    suspend fun deleteMovieFavorite(movieFavorite: MovieFavorite){
        movieFavoritesRepository.deleteMovieFavorite(movieFavorite)
        updateMovieFavoriteList()
    }
}