package com.example.movieapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.routes.Routes
import com.example.movieapp.ui.theme.MovieAppTheme


sealed class Destinations(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object HomeScreen : Destinations(
        route = Routes.Home.route,
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object MovieFavoriteScreen : Destinations(
        route = Routes.MovieFavorites.route,
        title = "Favorite",
        icon = Icons.Outlined.Favorite
    )

    object Logout : Destinations(
        route = Routes.Login.route,
        title = "Logout",
        icon = Icons.Outlined.ExitToApp
    )

}



@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun FavouriteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Magenta)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Favourite Screen",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun NotificationScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Notification Screen",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.HomeScreen.route) {
        composable(Destinations.HomeScreen.route) {
            HomeScreen()
        }
        composable(Destinations.MovieFavoriteScreen.route) {
            FavouriteScreen()
        }
        composable(Destinations.Logout.route) {
            NotificationScreen()
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun BottomBar(
    navController: NavHostController, modifier: Modifier = Modifier,
) {
    val screens = listOf(
        Destinations.HomeScreen, Destinations.MovieFavoriteScreen, Destinations.Logout
    )

    NavigationBar(
        modifier = modifier,
        containerColor = Color.LightGray,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->

            NavigationBarItem(
                label = {
                    Text(text = screen.title!!)
                },
                icon = {
                    Icon(imageVector = screen.icon!!, contentDescription = "")
                },
                selected = currentRoute == screen.route,
                onClick = {

                    if (screen.route == Routes.Login.route){
                        navController.navigate(screen.route)
                    }else{
                        navController.navigate(screen.route)
                    }


                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.Gray, selectedTextColor = Color.White
                ),
            )
        }
    }

}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun BottomNavPreview() {
    MovieAppTheme {
        val navController: NavHostController = rememberNavController()
        val bottomBarHeight = 56.dp
        val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }

        var buttonsVisible = remember { mutableStateOf(true) }

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
                NavigationGraph(navController = navController)
            }
        }
    }
}