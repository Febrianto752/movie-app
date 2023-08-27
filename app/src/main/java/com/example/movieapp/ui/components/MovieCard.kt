package com.example.movieapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberImagePainter
import com.example.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieCard(
    movie: String,
    isFavorite: Boolean,
    onToggleFavorite: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth().background(color = Color.Transparent),

        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            ){
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .padding(top = 12.dp, end = 12.dp)
                .zIndex(1f)){
                IconButton(
                    onClick = { onToggleFavorite(!isFavorite) },
                    modifier = Modifier
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            color = if (isFavorite) Color.Red else Color.LightGray
                        )
                        .align(Alignment.TopEnd)
                        .zIndex(2f)

                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = Color.White
                    )
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth().background(color = Color(
                        red = 0.1411765f,
                        green = 0.18039216f,
                        blue = 0.20392157f
                    ))
            ) {
                Image(
                    painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/original/jZIYaISP3GBSrVOPfrp98AMa8Ng.jpg"),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(100.dp, 200.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.height(8.dp))



                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)

                    )
                    Text(text = "4.5", fontSize = 16.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(4.dp))
                
                Text(text = "In a city where fire, water", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(8.dp))


            }
        }

    }
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MovieAppTheme {
        MovieCard(movie = "Hello", isFavorite = false, onToggleFavorite = {})
    }
}