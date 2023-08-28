package com.example.movieapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.movieapp.R
import com.example.movieapp.data.apis.CONFIG
import com.example.movieapp.data.models.Movie

class Carousel {}

@Composable
fun ImageCarousel(movies: List<Movie>, navController: NavController) {
    val configuration = LocalConfiguration.current
    val viewportWidth = configuration.screenWidthDp.dp
    println(viewportWidth)
    LazyRow(
        modifier = Modifier.padding(top = 8.dp),
//        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(movies) { movie ->

            Box(modifier = Modifier
                .width(viewportWidth - 20.dp)
                .height(240.dp)
                .padding(end = 8.dp)
                .clickable {
                    navController.navigate("MovieDetail/${movie.id}")
                }

            ) {
                Image(
                    painter = rememberImagePainter(data = "${CONFIG.URL_IMG}${movie.backdrop_path
                    }"),
                    contentDescription = "carousel image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()

                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(color = Color(0f, 0f, 0f, 0.3f))
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 10.dp, bottom = 10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rating",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)

                            )
                            Text(text = "${movie.vote_average}", fontSize = 18.sp, color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "${movie.title}",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )


                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun ImageCarouselPreview() {
    val topRatedMovies = listOf(
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
    val navController = rememberNavController()
    ImageCarousel(movies = topRatedMovies, navController = navController)
}