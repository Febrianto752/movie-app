package com.example.movieapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movieapp.MovieApplication
import com.example.movieapp.data.apis.MovieApiService
import com.example.movieapp.data.apis.retrofit
import com.example.movieapp.ui.viewModels.movie.MovieDetailViewModel
import com.example.movieapp.ui.viewModels.movie.MovieFavoriteViewModel
import com.example.movieapp.ui.viewModels.movie.MovieViewModel
import com.example.movieapp.ui.viewModels.user.UserViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MovieViewModel(retrofit.create(MovieApiService::class.java))
        }

        initializer {
            MovieDetailViewModel(retrofit.create(MovieApiService::class.java))
        }

        initializer {
            UserViewModel(movieApplication().container.usersRepository)
        }

        initializer {
            MovieFavoriteViewModel(movieApplication().container.movieFavoritesRepository)
        }

    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.movieApplication(): MovieApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication)
