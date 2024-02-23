package com.example.scoopsy.ui.navigation

import androidx.compose.runtime.Composable
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