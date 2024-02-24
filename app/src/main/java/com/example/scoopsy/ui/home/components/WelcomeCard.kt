package com.example.scoopsy.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scoopsy.R

@Composable
fun WelcomeCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.ice_cream_second),
            contentDescription = null,
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column {
            Spacer(modifier = modifier.size(10.dp))
            Text(
                text = "Welcome,",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 30.sp,
                modifier = modifier.padding(vertical = 10.dp, horizontal = 20.dp)
            )

        }
        Column(
            modifier = modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 15.dp, vertical = 10.dp)

        ) {
            Box(
                modifier = modifier
                    .background(Color.DarkGray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                    .padding(vertical = 5.dp, horizontal = 10.dp)
            ) {
                Text(
                    text = "Dive into a world of delightful flavors",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = modifier
                        .width(150.dp)
                        .padding(),
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}