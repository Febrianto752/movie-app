package com.example.movieapp.utilities

fun isEmailValid(email: String): Boolean {
    // A simple email validation logic using regular expression
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailRegex.toRegex())
}
