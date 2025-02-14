package com.sravani.randomusers.ui.splash

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sravani.randomusers.R
import com.sravani.randomusers.ui.base.Route
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000) // Delay for 3 seconds
        navController.navigate(Route.Home.name) {
            popUpTo(Route.Splash.name) { inclusive = true }
        }
    }
    Image(
        painter = painterResource(id = R.drawable.splash_img), // Replace with your background image
        contentDescription = "Splash Background",
        contentScale = ContentScale.Crop, // Scale image to cover the screen
        modifier = Modifier.fillMaxSize()
    )
}