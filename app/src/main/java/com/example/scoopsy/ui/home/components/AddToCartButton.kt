package com.example.scoopsy.ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scoopsy.data.ScoopsyUIState
import com.example.scoopsy.ui.ScoopsyViewModel
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun AddToCartButton(
    modifier: Modifier = Modifier,
    scoopsyViewModel: ScoopsyViewModel,
    scoopsyUIState: ScoopsyUIState,
    onAddAction: ()-> Unit
) {
    val currentPrice = String.format(
        Locale.US, "%.2f", scoopsyUIState.currentItemPrice
    )
    val scope = rememberCoroutineScope()
    ExtendedFloatingActionButton(
        onClick = {
            scope.launch {
                scoopsyViewModel.addItemToCart(
                    item = scoopsyUIState.currentItem,
                    quantity = scoopsyUIState.currentQuantity,
                    iceCreamType = if (!scoopsyUIState.currentItem.isPopular) {
                        scoopsyUIState.currentType
                    } else {
                        null
                    }
                )
                onAddAction()
            }

        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Text(text = "Add ${scoopsyUIState.currentQuantity} to cart • $$currentPrice")
    }
}