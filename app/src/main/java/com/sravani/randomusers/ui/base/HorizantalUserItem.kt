package com.sravani.randomusers.ui.base

import android.text.TextUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.sravani.randomusers.R
import com.sravani.randomusers.data.model.Location

@Composable
fun HorizontalUserItem(
    name: String,
    imageUrl: String,
    location: Location,
    onClick: () -> Unit
) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .build()
    ).state
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {onClick()})
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .align(CenterVertically)
                    .fillMaxWidth(0.3f),
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (imageState is AsyncImagePainter.State.Error) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp)
                                .height(90.dp)
                                .clip(androidx.compose.foundation.shape.CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                               ,
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(70.dp),
                                imageVector = Icons.Rounded.AccountBox,
                                contentDescription = imageUrl
                            )
                        }
                    }

                    if (imageState is AsyncImagePainter.State.Success) {
                        Image(
                            painter = imageState.painter,
                            contentDescription = imageUrl,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                                .border(
                                    2.dp,
                                    Color.Gray,
                                    CircleShape
                                )

                        )
                    }

                }

            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = name,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold, FontWeight.Bold)),
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (!TextUtils.isEmpty(location.getFormattedAddress())) location.getFormattedAddress() else "NA",
                    modifier = Modifier,
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium, FontWeight.Black))
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}