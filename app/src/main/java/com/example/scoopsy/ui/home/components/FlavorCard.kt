package com.example.scoopsy.ui.home.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.scoopsy.data.Item


@Composable
fun FlavorCard(modifier: Modifier = Modifier, flavor: Item, onItemClick: () -> Unit, context: Context) {
    ElevatedCard(
        onClick = { onItemClick() },
        modifier = modifier
            .height(120.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Box() {
            Image(
                painter = painterResource(id = flavor.image),
                contentDescription = null,
                modifier = modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = context.getString(flavor.name), modifier = modifier.padding(10.dp))
        }
    }
}