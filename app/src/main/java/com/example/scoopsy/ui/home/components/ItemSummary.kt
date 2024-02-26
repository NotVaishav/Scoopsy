package com.example.scoopsy.ui.home.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scoopsy.data.Item
import com.example.scoopsy.data.ScoopsyUIState
import com.example.scoopsy.ui.ScoopsyViewModel
import java.util.Locale

@Composable
fun ItemSummary(
    modifier: Modifier,
    item: Item,
    context: Context,
    onCloseClick: () -> Unit,
    scoopsyViewModel: ScoopsyViewModel,
    scoopsyUIState: ScoopsyUIState
) {
    val totalPrice = String.format(
        Locale.US, "%.2f", item.price
    )
    Scaffold(
        floatingActionButton = {
            AddToCartButton(
                scoopsyViewModel = scoopsyViewModel,
                scoopsyUIState = scoopsyUIState,
                onAddAction = onCloseClick
            )
        }, floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier.fillMaxSize()
                )
                Box(
                    modifier = modifier
                        .padding(5.dp)
                ) {
                    IconButton(
                        onClick = { onCloseClick() }, modifier = modifier

                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = null,
                            tint = Color.DarkGray.copy(alpha = 0.8f)
                        )
                    }
                }

            }
            Column(modifier = modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
                Text(
                    text = context.getString(item.name),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$${totalPrice}",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = modifier.size(10.dp))
                Text(text = item.description, lineHeight = 21.sp)
                HorizontalDivider(
                    modifier = modifier.padding(vertical = 10.dp),
                    thickness = 5.dp,
                    color = Color.LightGray.copy(alpha = 0.7f)
                )
            }
            if (!item.isPopular) {
                TypeRadioSection(
                    context = context,
                    scoopsyUIState = scoopsyUIState,
                    scoopsyViewModel = scoopsyViewModel
                )
                HorizontalDivider(
                    modifier = modifier.padding(vertical = 10.dp, horizontal = 15.dp),
                    thickness = 5.dp,
                    color = Color.LightGray.copy(alpha = 0.7f)
                )
            }
            QuantitySection(scoopsyUIState = scoopsyUIState, scoopsyViewModel = scoopsyViewModel)
            Spacer(modifier = modifier.size(85.dp))

        }
    }

}