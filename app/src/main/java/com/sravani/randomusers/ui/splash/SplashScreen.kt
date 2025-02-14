package com.sravani.randomusers.ui.splash

import android.window.SplashScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sravani.randomusers.R
import com.sravani.randomusers.ui.base.Route
import com.sravani.randomusers.ui.theme.DarkPrimaryColor
import com.sravani.randomusers.ui.theme.SplashScreenHeaderColor
import com.sravani.randomusers.utils.AppConstants
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var isVisible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        delay(100)
        isVisible = true
        delay(2500)
        navController.popBackStack()
        navController.navigate(Route.Home.name)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPrimaryColor),
        verticalArrangement = Arrangement.Center,
    ) {
        AnimatedVisibility(visible = isVisible, enter = slideInVertically(
            animationSpec = tween(
                2000, 100, FastOutSlowInEasing
            ),
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top, animationSpec = tween(
                2000, 100, FastOutSlowInEasing
            )
        ) ) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = AppConstants.APP_NAME,
                    style = TextStyle(
                        color = Color.White,
                        textAlign = TextAlign.End,
                        fontSize = MaterialTheme.typography.displaySmall.fontSize,
                        fontWeight = FontWeight(700),
                        shadow = Shadow(
                            color = SplashScreenHeaderColor, offset = Offset(10f, 10f)
                        )
                    ),
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}