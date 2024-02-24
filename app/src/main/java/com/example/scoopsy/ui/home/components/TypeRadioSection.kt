package com.example.scoopsy.ui.home.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scoopsy.data.IceCreamTypes
import java.util.Locale


@Composable
fun TypeRadioSection(modifier: Modifier = Modifier, context: Context) {
    val radioOptions = IceCreamTypes
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    Column(modifier = modifier.padding(horizontal = 15.dp)) {
        Text(
            text = "Choose Type",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        radioOptions.forEach { text ->
            val itemPrice = String.format(
                Locale.US, "%.2f", context.getString(text.price).toDouble()
            )
            Row(modifier = modifier
                .fillMaxWidth()
                .height(40.dp)
                .selectable(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionSelected(text)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Row(modifier = modifier.fillMaxWidth()) {
                    Text(
                        text = context.getString(text.type),
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
