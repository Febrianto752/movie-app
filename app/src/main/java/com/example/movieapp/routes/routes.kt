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


@ExperimentalMaterial3Api
@Composable
fun MovieAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(Routes.Register.route) {
            RegisterScreen(navController = navController)
        }

        composable(Routes.Home.route) {
            Scaffold(
                bottomBar = {
                    BottomBar(
                        navController = navController,

                        modifier = Modifier,
                    )
                }) { paddingValues ->
                Box(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    MovieList(navController = navController)
                }
            }
        }
//
//        composable(Routes.BookDetail.route) { backStackEntry ->
//            BookDetail(
//                navController = navController,
//                backStackEntry.arguments?.getString("bookIndex")?.toIntOrNull()
//            )
//        }

    }
}