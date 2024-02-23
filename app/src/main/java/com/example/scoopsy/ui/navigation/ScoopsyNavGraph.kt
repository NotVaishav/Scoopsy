package com.example.scoopsy.ui.navigation

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.scoopsy.ui.ScoopsyViewModel
import com.example.scoopsy.ui.cart.CartScreen
import com.example.scoopsy.ui.home.HomeScreen

enum class ScoopsyNavDestinations(val title: String, val route: String) {
    Home(title = "home", route = "homeScreen"),
    Cart(title = "cart", route = "cartScreen"),
}


@Composable
fun ScoopsyNavGraph(
    navController: NavHostController,
    scoopsyViewModel: ScoopsyViewModel = ScoopsyViewModel(),
) {
    NavHost(
        navController = navController,
        startDestination = ScoopsyNavDestinations.Home.title
    ) {
        composable(route = ScoopsyNavDestinations.Home.title) {
            HomeScreen()
        }
        composable(route = ScoopsyNavDestinations.Cart.title) {
            CartScreen()
        }
    }
}
