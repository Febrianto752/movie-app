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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.components.BottomBar
import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieFavorites(navController: NavHostController) {
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

            LazyVerticalGrid(

                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    items(10) { index ->
                        MovieCard(movie = "", isFavorite = true, onToggleFavorite = {})
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            )


        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MovieFavoritesPreview() {
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