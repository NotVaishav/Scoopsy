package com.example.scoopsy.ui.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scoopsy.R
import com.example.scoopsy.data.CartItem
import com.example.scoopsy.ui.ScoopsyViewModel
import kotlinx.coroutines.launch


@Composable
fun CartItemCard(
    modifier: Modifier = Modifier,
    item: CartItem,
    scoopsyViewModel: ScoopsyViewModel
) {
    val context = LocalContext.current
    val subTextValue = if (item.item.isPopular) {
        item.item.subType
    } else {
        item.iceCreamType?.type
    }
    val isPopular = item.item.isPopular
    val totalItemPrice = item.quantity * item.eachItemPrice
    val scope = rememberCoroutineScope()



    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = item.item.image),
                contentDescription = null,
                modifier = modifier
                    .size(width = 100.dp, height = 120.dp)
                    .shadow(8.dp, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(start = 15.dp)
            ) {
                Column {
                    Spacer(modifier = modifier.size(1.dp))
                    Text(
                        text = context.getString(item.item.name),
                        fontWeight = FontWeight.Bold,
                    )
                    Row {
                        Text(text = context.getString(subTextValue!!), fontSize = 12.sp)
                        if (!isPopular && item.iceCreamType?.price != 0.00) {
                            Text(
                                text = "+$${item.iceCreamType?.price}",
                                fontSize = 10.sp,
                                modifier = modifier.padding(start = 3.dp)
                            )
                        }
                    }
                    if (isPopular) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.star),
                                contentDescription = null,
                                modifier = modifier.size(14.dp),
                                tint = Color(0xFFf1af09)
                            )
                            Text(
                                text = "Popular Item",
                                fontSize = 10.sp,
                                modifier = modifier.padding(start = 5.dp, top = 3.dp),
                            )
                        }
                    } else {
                        Text(
                            text = " ",
                            fontSize = 10.sp,
                            modifier = modifier.padding(horizontal = 5.dp)
                        )
                    }
                    Spacer(modifier = modifier.size(10.dp))
                    Text(text = "$$totalItemPrice")
                }
            }
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.End,
            ) {
                Box(
                    modifier = modifier
                        .padding(6.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .clickable {
                            scope.launch {
                                scoopsyViewModel.deleteItemFromCart(item)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        modifier = modifier.size(22.dp),
                        tint = Color(0xFFde2108)
                    )
                }
                Spacer(modifier = modifier.size(40.dp))
                CartItemQuantityPicker(
                    currentQuantity = item.quantity,
                    onQuantityDecrease = { scoopsyViewModel.decreaseCartItemQuantity(item) },
                    onQuantityIncrease = { scoopsyViewModel.increaseCartItemQuantity(item) }
                )
            }

        }
    }
}
