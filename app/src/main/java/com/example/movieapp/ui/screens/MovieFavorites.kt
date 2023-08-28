package com.example.movieapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.data.models.Movie
import com.example.movieapp.data.models.toMovie
import com.example.movieapp.ui.components.BottomBar
import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.viewModels.AppViewModelProvider
import com.example.movieapp.ui.viewModels.movie.MovieDetailViewModel
import com.example.movieapp.ui.viewModels.movie.MovieFavoriteViewModel
import com.example.movieapp.ui.viewModels.user.UserViewModel


@ExperimentalMaterial3Api
@Composable
fun MovieFavorites(
    navController: NavHostController,
    movieFavoriteViewModel: MovieFavoriteViewModel = viewModel(factory = AppViewModelProvider.Factory),
    userViewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var userLogged = userViewModel.usersList.find {
        it.isLogin == true;
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(
                    red = 0.1411765f,
                    green = 0.18039216f,
                    blue = 0.20392157f
                )
            )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "My Movie Favorites",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))
            if (movieFavoriteViewModel.movieFavoriteList.isNotEmpty() && userLogged != null){
                var movieFavoritesUser = movieFavoriteViewModel.movieFavoriteList.filter {
                    it.user_id == userLogged.id
                }
                LazyVerticalGrid(

                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    content = {
                        items(movieFavoritesUser) { movieFavorite ->
                            MovieCard(
                                movie = movieFavorite.toMovie(),
                                navController = navController
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                )
            }else{
                Text(text = "My Favorite Movie is Empty", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, color = Color.White)
            }
            


        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MovieFavoritesPreview() {
    val favoriteMovies = listOf(
        Movie(
            238,
            false,
            "/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
            listOf(18, 80),
            "en",
            "The Godfather",
            "Spanning the years 1945 to 1955...",
            121.292,
            "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            "1972-03-14",
            "The Godfather",
            false,
            8.7,
            18492
        ), Movie(
            id = 278,
            adult = false,
            backdrop_path = "/kXfqcdQKsToO0OUXHcrrNCHDBzO.jpg",
            genre_ids = listOf(18, 80),
            original_language = "en",
            original_title = "The Shawshank Redemption",
            overview = "Framed in the 1940s for the double murder of his wife and her lover...",
            popularity = 125.723,
            poster_path = "/lyQBXzOQSuE59IsHyhrp0qIiPAz.jpg",
            release_date = "1994-09-23",
            title = "The Shawshank Redemption",
            video = false,
            vote_average = 8.7,
            vote_count = 24440
        ), Movie(
            id = 240,
            adult = false,
            backdrop_path = "/oo4PVK6AyLZN49BokxDFGyclN86.jpg",
            genre_ids = listOf(18, 80),
            original_language = "en",
            original_title = "The Godfather Part II",
            overview = "In the continuing saga of the Corleone crime family, a young Vito Corleone grows up in Sicily and in 1910s New York. In the 1950s, Michael Corleone attempts to expand the family business into Las Vegas, Hollywood and Cuba.",
            popularity = 71.015,
            poster_path = "/bMadFzhjy9T7R8J48QGq1ngWNAK.jpg",
            release_date = "1974-12-20",
            title = "The Godfather Part II",
            video = false,
            vote_average = 8.6,
            vote_count = 11178
        )
        // Add more image resource IDs here
    )
    val navController: NavHostController = rememberNavController()
    MovieAppTheme {

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
                MovieFavorites(navController)
            }
        }
    }
}