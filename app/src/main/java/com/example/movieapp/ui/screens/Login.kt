package com.example.movieapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.Greeting
import kotlinx.coroutines.launch
import com.example.movieapp.R
import com.example.movieapp.routes.Routes
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.viewModels.AppViewModelProvider
import com.example.movieapp.ui.viewModels.user.UserViewModel

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
    ) {
    var userLogged = viewModel.usersList.find {
        it.isLogin == true
    }

    if (userLogged != null){
        navController.navigate(Routes.Home.route)
    }

    Scaffold() { padding ->
        Column(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val email = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }
            var showToast = remember { mutableStateOf(false) }

            val coroutineScope = rememberCoroutineScope()

            Text(
                text = "eSFi",
                fontSize = 22.sp,

                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Black, Color.Red
                        )
                    )
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )

            Image(
                painter = painterResource(id = com.example.movieapp.R.drawable.ic_movie),
                contentDescription = "profile image",
                modifier = Modifier

                    .height(200.dp)
                    .padding(top = 50.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "Email") },
                value = email.value,
                onValueChange = { email.value = it })

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "Password") },
                value = password.value,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { password.value = it })

            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        var user = viewModel.usersList.find{
                            it.email == email.value && it.password == password.value
                        }

                        if (user != null){
                            user.isLogin = true;
                            coroutineScope.launch {
                                viewModel.updateUser(user)
                            }
                            navController.navigate(Routes.Home.route)
                        }else{
                            showToast.value = true;
                        }

                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(start = 30.dp, end = 30.dp)
                ) {
                    Text(text = "Login")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            ClickableText(
                text = text,
                onClick = { offset ->
                    text.getStringAnnotations(
                        tag = "URL",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let { annotation ->

                        navController.navigate(Routes.Register.route)
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (showToast.value) {
                Snackbar(
                    action = {
                        TextButton(onClick = { showToast.value = false }) {
                            Text("Dismiss")
                        }
                    }
                ) {
                    Text("Email or password is wrong!")
                }
            }
        }
    }

}

private val text = AnnotatedString.Builder("Don't have account? register!")
    .apply {
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            start = 20, // Start index of the link text
            end = 29   // End index of the link text
        )

        // Add a string annotation for the link click
        addStringAnnotation(
            tag = "URL",
            annotation = "https://www.example.com",
            start = 20,
            end = 29
        )
    }
    .toAnnotatedString()


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    MovieAppTheme {
        LoginScreen(navController)
    }
}
