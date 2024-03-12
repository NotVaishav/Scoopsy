package com.example.scoopsy.ui.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scoopsy.R


@Composable
fun CartItemQuantityPicker(
    modifier: Modifier = Modifier,
    currentQuantity: Int,
    onQuantityIncrease: () -> Unit,
    onQuantityDecrease: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(
                color = Color.LightGray.copy(alpha = 0.4f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 4.dp, vertical = 3.dp)
    ) {
        Box(
            modifier = modifier
                .padding(6.dp)
                .size(16.dp)
                .clip(CircleShape)
                .clickable(enabled = currentQuantity != 1) { onQuantityDecrease() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.minus_sign),
                contentDescription = null,
                modifier = modifier
                    .size(10.dp),
                tint = if (currentQuantity != 1) {
                    Color.Black
                } else {
                    Color.Gray
                }
            )
        }
        Text(
            text = currentQuantity.toString(),
            modifier = modifier
                .padding(horizontal = 5.dp)
                .width(15.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
        Box(
            modifier = modifier
                .padding(6.dp)
                .size(16.dp)
                .clip(CircleShape)
                .clickable() { onQuantityIncrease() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus_sign),
                contentDescription = null,
                modifier = modifier
                    .size(10.dp),
            )
        }
    }
}