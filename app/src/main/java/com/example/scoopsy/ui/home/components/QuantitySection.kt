package com.example.scoopsy.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scoopsy.R
import com.example.scoopsy.data.ScoopsyUIState
import com.example.scoopsy.ui.ScoopsyViewModel


@Composable
fun QuantitySection(
    modifier: Modifier = Modifier,
    scoopsyUIState: ScoopsyUIState,
    scoopsyViewModel: ScoopsyViewModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Quantity", fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        QuantityPicker(scoopsyViewModel = scoopsyViewModel, scoopsyUIState = scoopsyUIState)
    }
}

@Composable
fun QuantityPicker(
    modifier: Modifier = Modifier,
    scoopsyViewModel: ScoopsyViewModel,
    scoopsyUIState: ScoopsyUIState
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.background(
            color = Color.LightGray.copy(alpha = 0.4f),
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        IconButton(
            onClick = { scoopsyViewModel.decreaseQuantity() },
            enabled = !scoopsyUIState.reachedMinimumQuantity
        ) {
            Icon(
                painter = painterResource(id = R.drawable.minus_sign),
                contentDescription = null,
                modifier = modifier.size(12.dp)
            )
        }
        Text(
            text = scoopsyUIState.currentQuantity.toString(),
            modifier = modifier.padding(horizontal = 5.dp).width(20.dp),
            textAlign = TextAlign.Center
        )
        IconButton(
            onClick = { scoopsyViewModel.increaseQuantity() },
            enabled = !scoopsyUIState.reachedMaximumQuantity
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus_sign),
                contentDescription = null,
                modifier = modifier.size(12.dp)
            )
        }
    }


}