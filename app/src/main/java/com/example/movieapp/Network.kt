package com.example.movieapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview


fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

@Composable
fun OfflineAwareContent() {
    val context = LocalContext.current
    val isOnline = isNetworkAvailable(context)

    if(isOnline){
        Text("Online")
    }else{
        Text("Offline")
    }

    // ... Rest of the content handling based on isOnline status
}

@Preview
@Composable
fun OfflineAwareContentPreview() {
    OfflineAwareContent()
}