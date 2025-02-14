package com.sravani.randomusers.ui.base

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.sravani.randomusers.data.model.User
import com.sravani.randomusers.ui.details.DetailsScreen
import com.sravani.randomusers.ui.splash.SplashScreen
import com.sravani.randomusers.ui.userlist.HomeRoute



sealed class Route(val name:String){
    object Home: Route("Home")
    object Splash: Route("Splash")
    object Details : Route("Details/{user}") {
        fun createRoute(userJson: String) = "Details/$userJson"
    }
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
        composable(
            route = Route.Details.name,
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { backStackEntry ->
            val encodedUserJson = backStackEntry.arguments?.getString("user")
            // URL-decode the JSON string and deserialize it back to a User object
            val user = encodedUserJson?.let {
                val decodedUserJson = Uri.decode(it)
                Gson().fromJson(decodedUserJson, User::class.java)
            }
            DetailsScreen(user, navController)
        }
    }
}
