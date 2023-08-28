package com.example.movieapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.R
import com.example.movieapp.routes.Routes
import com.example.movieapp.ui.components.BottomBar
import com.example.movieapp.ui.components.Carousel
import com.example.movieapp.ui.components.ImageCarousel
import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.ui.components.NavigationGraph
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.viewModels.AppViewModelProvider
import com.example.movieapp.ui.viewModels.movie.MovieViewModel
import com.example.movieapp.ui.viewModels.user.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@ExperimentalMaterial3Api
@Composable
fun MovieList(
    navController: NavHostController,
    movieViewModel: MovieViewModel = viewModel(factory = AppViewModelProvider.Factory),
    userViewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var userLogged = userViewModel.usersList.find{
        it.isLogin == true;
    }


    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Welcome ${userLogged?.name ?: ""}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                    )
                })
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .background(
                    color = Color(
                        red = 0.1411765f,
                        green = 0.18039216f,
                        blue = 0.20392157f
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 20.dp)
                    .verticalScroll(scrollState)
            ) {
                Text(
                    "Top Rated",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White
                )

                ImageCarousel(movies = movieViewModel.topRatedMovies, navController = navController)

                Spacer(modifier = Modifier.height(24.dp))

                Text("Popular", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)

                LazyRow(
                    modifier = Modifier.padding(top = 8.dp),
                ) {
                    items(movieViewModel.popularMovies) { movie ->
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .width(200.dp)
                        ) {
                            MovieCard(
                                movie = movie,
                                navController = navController
                            )
                        }

                    }

                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "Up Coming",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )

                LazyRow(
                    modifier = Modifier.padding(top = 8.dp),
                ) {
                    items(movieViewModel.upComingMovies) { movie ->
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .width(200.dp)
                                .background(color = Color.Transparent)
                        ) {
                            MovieCard(
                                movie = movie,
                                navController = navController
                            )
                        }

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

            }
        }

    }
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    val navController = rememberNavController()
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
                MovieList(navController)
            }
        }

    }
}