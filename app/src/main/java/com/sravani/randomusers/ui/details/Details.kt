package com.sravani.randomusers.ui.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sravani.randomusers.R
import com.sravani.randomusers.data.model.User
import com.sravani.randomusers.ui.base.RowTextDetails
import com.sravani.randomusers.utils.Utils

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(user: User?, navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GradientProfilePic(imageURL = user?.picture?.large)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${user?.name?.title} ${user?.name?.first} ${user?.name?.last}",
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold, FontWeight.Bold)),
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            RowTextDetails(text = user?.email ?: "", Icons.Rounded.Email)
            Spacer(modifier = Modifier.height(8.dp))
            RowTextDetails(text = "Phone : ${user?.phone}", Icons.Rounded.Phone)
            Spacer(modifier = Modifier.height(8.dp))
            RowTextDetails(
                text = "DOB : ${user?.dob?.date?.let { Utils.formatDob(it) }}",
                Icons.Rounded.DateRange
            )
            Spacer(modifier = Modifier.height(8.dp))
            RowTextDetails(
                text = "${user?.location?.city}, ${user?.location?.state}, ${user?.location?.country}",
                Icons.Rounded.LocationOn
            )
        }
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Phone Icon",
            tint = Color.White,
            modifier = Modifier.size(45.dp)
                .padding(top = 16.dp, start = 16.dp)
                .clickable { navController.popBackStack() },
        )
    }
}

@Composable
fun GradientProfilePic(imageURL: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF512DA8),
                            Color(0xFF673AB7), // Medium Purple
                            Color(0xFF3F51B5)  // Indigo
                        )
                    )
                )
        )
        Image(
            painter = rememberAsyncImagePainter(imageURL),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .border(4.dp, Color.White, CircleShape)
                .align(Alignment.BottomCenter)
        )
    }
}
