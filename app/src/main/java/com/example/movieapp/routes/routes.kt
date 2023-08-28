package com.example.movieapp.routes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.components.BottomBar
import com.example.movieapp.ui.screens.LoginScreen
import com.example.movieapp.ui.screens.MovieFavorites
import com.example.movieapp.ui.screens.MovieList
import com.example.movieapp.ui.screens.RegisterScreen

sealed class Routes(val route: String) {
    object Login : Routes("Login")

    // movie list
    object Home : Routes("Home")
    object MovieFavorites : Routes("MovieFavorite")
    object MovieDetail : Routes("MovieDetail/{movieId}")
    object Register : Routes("Register")

}


