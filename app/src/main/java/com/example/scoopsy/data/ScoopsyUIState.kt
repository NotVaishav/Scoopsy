package com.example.scoopsy.data

data class ScoopsyUIState(
    val totalPrice: Double = 0.0,
    val isItemInCart: Boolean = false,
    val cartItems: List<CartItem> = listOf()
)

data class CartItem(var item: Flavor, var iceCreamType: IceCreamType, var quantity: Int)