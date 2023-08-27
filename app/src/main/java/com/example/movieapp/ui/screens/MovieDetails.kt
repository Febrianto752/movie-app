package com.example.movieapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.movieapp.ui.components.Badge
import com.example.movieapp.ui.components.BottomBar
import com.example.movieapp.ui.theme.MovieAppTheme


@Composable
fun MovieDetails(navController: NavHostController, movieId: Int?) {
    println(movieId)
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .zIndex(2f)
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(16.dp),
                        color = Color.Red
                    )
                    .align(Alignment.TopEnd)


            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Remove from favorites",
                    tint = Color.White
                )
            }
        }


        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .background(
                    color = Color(
                        red = 0.1411765f,
                        green = 0.18039216f,
                        blue = 0.20392157f
                    )
                )
                .verticalScroll(scrollState)
        ) {
            val configuration = LocalConfiguration.current
            val viewportWidth = configuration.screenWidthDp.dp
            Image(
                painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/original/8riWcADI1ekEiBguVB9vkilhiQm.jpg"),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(0.dp, 400.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(text = "4.5", fontSize = 16.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "In a city where fire, water",
                    fontSize = 26.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Badge(text = "Comedy")
                    Badge(text = "Horror")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
                            "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
                            "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
                            "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
                            "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
                            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Powered By TMDB",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

            }

        }
    }
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
    val navController: NavHostController = rememberNavController()
    MovieAppTheme {

        MovieDetails(navController, 123)
    }
}