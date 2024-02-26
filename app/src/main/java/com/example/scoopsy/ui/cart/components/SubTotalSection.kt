package com.example.scoopsy.ui.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.scoopsy.data.ScoopsyUIState
import com.example.scoopsy.ui.ScoopsyViewModel
import java.util.Locale

@Composable
fun SubTotalSection(
    modifier: Modifier = Modifier,
    scoopsyViewModel: ScoopsyViewModel,
    scoopsyUIState: ScoopsyUIState
) {
    val currentPrice = String.format(
        Locale.US, "%.2f", scoopsyUIState.totalPrice
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 5.dp
            )
    ) {
        Text(
            text = "Subtotal",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$$currentPrice",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}