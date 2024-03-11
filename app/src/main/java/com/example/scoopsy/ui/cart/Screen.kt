package com.example.scoopsy.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import androidx.navigation.NavController
import com.example.scoopsy.R
import com.example.scoopsy.data.CartItem
import com.example.scoopsy.ui.ScoopsyViewModel
import com.example.scoopsy.ui.cart.components.PlaceOrderButton
import com.example.scoopsy.ui.cart.components.SubTotalSection
import com.example.scoopsy.ui.home.components.TopBar
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    scoopsyViewModel: ScoopsyViewModel
) {
    val scoopsyUIState by scoopsyViewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                showNavButton = true,
                titleText = context.getString(R.string.my_cart)
            )
        },
        modifier = modifier
            .paint(
                painterResource(id = R.drawable.background_main),
                alpha = 0.2f,
                contentScale = ContentScale.Crop
            )
            .fillMaxSize(),
        containerColor = Color.Transparent,
        floatingActionButton = {
            if (scoopsyUIState.cartItems.isNotEmpty()) {
                PlaceOrderButton(onClickHandler = { scoopsyViewModel.setDialogValue(true) })
            }
        }, floatingActionButtonPosition = FabPosition.Center
    )
    { contentPadding ->
        if (scoopsyUIState.cartItems.isEmpty()) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = context.getString(R.string.no_items), textAlign = TextAlign.Center)
            }

        } else {
            Column(
                modifier = modifier
                    .padding(contentPadding)
                    .padding(horizontal = 15.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                for (item in scoopsyUIState.cartItems) {
                    CartItemCard(item = item, scoopsyViewModel = scoopsyViewModel)
                }
                HorizontalDivider(
                    modifier = modifier.padding(vertical = 10.dp),
                    thickness = 5.dp,
                    color = Color.LightGray.copy(alpha = 0.7f)
                )
                SubTotalSection(
                    scoopsyUIState = scoopsyUIState,
                    scoopsyViewModel = scoopsyViewModel
                )
                Spacer(modifier = modifier.size(80.dp))
            }
            if (scoopsyUIState.showDialog) {
                BasicAlertDialog(onDismissRequest = {

                    scoopsyViewModel.resetApp()
                    scoopsyViewModel.setDialogValue(false)
                    navController.popBackStack()


                }) {
                    ElevatedCard(
                        elevation = CardDefaults.elevatedCardElevation(8.dp),
                        modifier = modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(vertical = 8.dp),

                        ) {
                        Box(
                            modifier = modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = context.getString(R.string.order_placed),
                            )
                        }
                    }
                }
            }
        }


    }
}

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
//                tint = if (currentQuantity != 10) {
//                    Color.Black
//                } else {
//                    Color.Gray
//                }
            )
        }
    }
}