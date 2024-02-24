package com.example.scoopsy.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Scoopsy") },
        actions = {
            Box(modifier = modifier, contentAlignment = Alignment.TopEnd) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = null
                )
                Box(
                    modifier = modifier
                        .background(
                            color = Color.Red,
                            shape = CircleShape
                        )
                        .size(8.dp)
                        .fillMaxSize()
                ) {
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        modifier = modifier.padding(horizontal = 20.dp),
    )
}