package com.example.scoopsy.ui.home.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scoopsy.data.IceCreamTypes
import com.example.scoopsy.data.ScoopsyUIState
import com.example.scoopsy.ui.ScoopsyViewModel
import java.util.Locale


@Composable
fun TypeRadioSection(
    modifier: Modifier = Modifier,
    context: Context,
    scoopsyUIState: ScoopsyUIState,
    scoopsyViewModel: ScoopsyViewModel
) {
    val radioOptions = IceCreamTypes
    Column(modifier = modifier.padding(horizontal = 15.dp)) {
        Text(
            text = "Choose Type",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = modifier.size(8.dp))
        radioOptions.forEach { item ->
            val itemPrice = String.format(
                Locale.US, "%.2f", item.price
            )
            Row(modifier = modifier
                .fillMaxWidth()
                .height(40.dp)
                .selectable(
                    selected = (item == scoopsyUIState.currentType),
                    onClick = {
                        scoopsyViewModel.selectCurrentType(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (item == scoopsyUIState.currentType),
                    onClick = { scoopsyViewModel.selectCurrentType(item) }
                )
                Row(modifier = modifier.fillMaxWidth()) {
                    Text(
                        text = context.getString(item.type),
                        modifier = modifier.weight(1f)
                    )
                    if (itemPrice != "0.00") {
                        Text(
                            text = "+ $${itemPrice}",
                        )
                    }
                }

            }
        }
    }
}
