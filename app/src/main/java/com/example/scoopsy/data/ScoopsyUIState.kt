package com.example.scoopsy.data

data class ScoopsyUIState(
    var totalPrice: Double = 0.0,
    var isItemInCart: Boolean = false,
    var currentItem: Item? = null,
    var cartItems: List<CartItem> = listOf()
)

data class CartItem(var item: Item, var iceCreamType: IceCreamType, var quantity: Int)