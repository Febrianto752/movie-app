package com.example.movieapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import coil.compose.rememberImagePainter
import com.example.movieapp.R

class Carousel {
}
@Composable
fun ImageCarousel(images: List<Int>) {
    val configuration = LocalConfiguration.current
    val viewportWidth = configuration.screenWidthDp.dp
    println(viewportWidth)
    LazyRow(
        modifier = Modifier.padding(top = 8.dp),
//        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(images) { imageResource ->

            Box(modifier = Modifier
                .width(viewportWidth - 20.dp)
                .height(240.dp).padding(end = 8.dp)){
                Image(
                    painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/original/jZIYaISP3GBSrVOPfrp98AMa8Ng.jpg"),
                    contentDescription = "carousel image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()

                )
                
                Box(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(color = Color(0f, 0f, 0f, 0.3f))){
                    Column(modifier = Modifier.align(Alignment.BottomStart).padding(start = 10.dp, bottom = 10.dp)){
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rating",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)

                            )
                            Text(text = "4.5", fontSize = 18.sp, color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(text = "In a city where fire, water", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)


                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun ImageCarouselPreview() {
    val imageList = listOf(
        R.drawable.ic_movie,
        R.drawable.ic_movie,
        R.drawable.ic_launcher_background,
        // Add more image resource IDs here
    )
    ImageCarousel(images = imageList)
}