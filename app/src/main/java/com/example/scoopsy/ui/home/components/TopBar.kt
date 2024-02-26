package com.example.scoopsy.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import com.example.scoopsy.R
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.scoopsy.ui.navigation.ScoopsyNavDestinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    showActionButton: Boolean = false,
    showNavButton: Boolean = false,
    titleText: String
) {
    CenterAlignedTopAppBar(
        title = { Text(text = titleText) },
        navigationIcon = {
            if (showNavButton) {

                Icon(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = null,
                    modifier = modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .clickable { navController.popBackStack() }
                )

            }
        },
        actions = {
            if (showActionButton) {
                Box(
                    modifier = modifier
                        .clip(CircleShape)
                        .wrapContentSize()
                        .size(30.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = true),
                            onClick = {
                                navController.navigate(ScoopsyNavDestinations.Cart.title)
                            },
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = modifier, contentAlignment = Alignment.TopEnd
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = null,
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
                }
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        modifier = modifier.padding(horizontal = 20.dp),
    )
}