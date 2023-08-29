package com.example.movieapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
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
import com.example.movieapp.R
import com.example.movieapp.data.models.User
import com.example.movieapp.routes.Routes
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.viewModels.AppViewModelProvider
import com.example.movieapp.ui.viewModels.user.UserViewModel
import com.example.movieapp.utilities.isEmailValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@ExperimentalMaterial3Api
@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollState = rememberScrollState()
    Scaffold() { padding ->
        Column(
            modifier = Modifier
                .padding(20.dp, top = 80.dp)
                .verticalScroll(scrollState),
//        verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val username = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }

            val email = remember { mutableStateOf("") }
            var showToast = remember { mutableStateOf(false) }
            var isError = remember { mutableStateOf(false) }

            var errorMessage = remember { mutableStateOf("") }

            val coroutineScope = rememberCoroutineScope()

            Text(
                text = "Create an Account",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "Name") },
                value = username.value,
                onValueChange = { username.value = it })

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
                        if (username.value != "" && email.value != "" && password.value != "") {

                            if (isEmailValid(email.value)) {

                                var user = User(
                                    name = username.value,
                                    email = email.value,
                                    password = password.value
                                )


                                var emailHasBeenUsed = false
                                viewModel.usersList.forEach {
                                    if (it.email == user.email) {
                                        emailHasBeenUsed = true;
                                    }

                                }

                                if (emailHasBeenUsed == false) {
                                    coroutineScope.launch {
                                        viewModel.createUser(user)
                                    }

                                    showToast.value = true
                                    username.value = ""
                                    email.value = ""
                                    password.value = ""

                                } else {
                                    errorMessage.value = "email sudah terpakai";
                                    showToast.value = true;
                                    isError.value = true;
                                }
                            } else {
                                errorMessage.value = "email is not valid";
                                showToast.value = true;
                                isError.value = true;
                            }


                        } else {
                            errorMessage.value = "all fields are required";
                            showToast.value = true;
                            isError.value = true;
                        }
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Register")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (showToast.value) {
                Snackbar(
                    action = {
                        TextButton(onClick = {
                            showToast.value = false; isError.value = false; errorMessage.value = ""
                        }) {
                            Text("Dismiss")
                        }
                    }
                ) {
                    if (isError.value == true) {
                        Text(errorMessage.value)
                    } else {
                        Text("Successfully registered account")
                    }

                }
            }

            ClickableText(
                text = text,
                onClick = { offset ->
                    text.getStringAnnotations(
                        tag = "URL",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let { annotation ->
                        navController.navigate(Routes.Login.route)
                    }
                }
            )

//            LazyColumn(
//                modifier = Modifier
//                    .padding(top = 100.dp)
//                    .heightIn(0.dp, 300.dp)
//            ) {
//                items(viewModel.usersList) { user ->
//
//                    Column() {
//                        Text(user.name)
//                        Text(user.email)
//                        Text(user.password)
//
//                        Button(onClick = {
//                            coroutineScope.launch {
//                                viewModel.deleteUser(user)
//                            }
//                        }) {
//                            Text(text = "Delete ${user.name} ${user.email}")
//                        }
//
//                        Button(onClick = {
//                            user.isLogin = true
//                            coroutineScope.launch {
//                                viewModel.updateUser(user)
//                            }
//                        }) {
//                            Text(text = "Update ${user.name} ${user.email}")
//                        }
//                    }
//
//                }
//
//
//            }
//
//            Button(onClick = {
//                coroutineScope.launch {
//                    withContext(Dispatchers.IO) {
//                        // Operasi database dijalankan di Dispatchers.IO
//                        viewModel.deleteAllUser()
//                    }
//
//                }
//            }) {
//                Text(text = "Delete All User")
//            }


        }
    }

}

private val text = AnnotatedString.Builder("Already have an account? login!")
    .apply {
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            start = 25, // Start index of the link text
            end = 31   // End index of the link text
        )

        // Add a string annotation for the link click
        addStringAnnotation(
            tag = "URL",
            annotation = "https://www.example.com",
            start = 25,
            end = 31
        )
    }
    .toAnnotatedString()


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    MovieAppTheme {
        RegisterScreen(navController)
    }
}


