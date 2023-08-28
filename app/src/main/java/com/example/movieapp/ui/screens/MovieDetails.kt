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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.movieapp.ui.components.Badge
import com.example.movieapp.ui.components.BottomBar
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.viewModels.AppViewModelProvider
import com.example.movieapp.ui.viewModels.movie.MovieDetailViewModel
import com.example.movieapp.ui.viewModels.movie.MovieViewModel
import kotlinx.coroutines.launch


@Composable
fun MovieDetails(
    navController: NavHostController,
    movieId: Int?,
    viewModel: MovieDetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    if (movieId != null){
        LaunchedEffect(Unit) {

            coroutineScope.launch {

                viewModel.setMovieDetail(movieId)

            }
        }
    }
    
    if (viewModel.movieDetail != null){
        val scrollState = rememberScrollState()
        Box(modifier = Modifier.fillMaxWidth().verticalScroll(scrollState)) {
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



            Column(
                modifier = Modifier
                    .background(
                        color = Color(
                            red = 0.1411765f,
                            green = 0.18039216f,
                            blue = 0.20392157f
                        )
                    )

            ) {
                val configuration = LocalConfiguration.current
                val viewportWidth = configuration.screenWidthDp.dp
                Image(
                    painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/original${viewModel.movieDetail?.poster_path}"),
                    contentDescription = "movie detail",
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
                        Text(text = "${String.format("%.1f", viewModel.movieDetail?.vote_average)}", fontSize = 16.sp, color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${viewModel.movieDetail?.title}",
                        fontSize = 26.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        viewModel.movieDetail?.genres?.forEach {
                            Badge(text = "${it.name}")
                        }

                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "${viewModel.movieDetail?.overview}",
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
    }else{
        Text(text = "Loading...")
    }
    
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
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
                MovieDetails(navController, 278)
            }
        }
    }
}