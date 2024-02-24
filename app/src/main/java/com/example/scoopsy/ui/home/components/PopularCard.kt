package com.example.scoopsy.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scoopsy.data.Item
import java.util.Locale


@Composable
fun PopularCard(
    modifier: Modifier = Modifier,
    popularItem: Item,
    onItemClick: () -> Unit
) {
    val context = LocalContext.current
    val totalPrice = String.format(
        Locale.US, "%.2f", context.getString(popularItem.price).toDouble()
    )

    ElevatedCard(
        onClick = { onItemClick() },
        modifier = modifier
            .size(width = 140.dp, height = 180.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp, pressedElevation = 16.dp),
    ) {
        Box(modifier = modifier) {
            Image(
                painter = painterResource(id = popularItem.image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = modifier.background(
                    Color.DarkGray.copy(alpha = 0.5f),
                    RoundedCornerShape(bottomEnd = 8.dp)
                )
            ) {
                Column(
                    modifier = modifier
                        .align(Alignment.TopStart)
                        .padding(horizontal = 5.dp, vertical = 5.dp)

                ) {
                    Text(
                        text = context.getString(popularItem.name),
                        color = MaterialTheme.colorScheme.surface,
                        style = MaterialTheme.typography.titleSmall,
                    )
                    Text(
                        text = context.getString(popularItem.subType!!),
                        color = MaterialTheme.colorScheme.surface,
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 11.sp,
                    )
                }
            }

            Box(
                modifier = modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(topStart = 8.dp)
                    )
                    .shadow(elevation = 16.dp, shape = RoundedCornerShape(topStart = 8.dp))
                    .clip(
                        RoundedCornerShape(topStart = 8.dp)
                    )
                    .align(Alignment.BottomEnd)
            ) {

                Text(
                    text = "$${totalPrice}",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = modifier
                        .padding(vertical = 2.dp, horizontal = 4.dp),
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}
