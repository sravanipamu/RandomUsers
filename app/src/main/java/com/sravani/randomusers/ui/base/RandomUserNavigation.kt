package com.sravani.randomusers.ui.base

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sravani.randomusers.ui.splash.SplashScreen
import com.sravani.randomusers.ui.userlist.HomeRoute


sealed class Route(val name:String){
    object Home: Route("Home")
    object Splash: Route("Splash")
}
@Composable
fun RandomUserNavHost(){
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Route.Splash.name){
        composable(route = Route.Splash.name){
            SplashScreen(navController)
        }
        composable(route = Route.Home.name){
            HomeRoute(navController)
        }
    }
}
