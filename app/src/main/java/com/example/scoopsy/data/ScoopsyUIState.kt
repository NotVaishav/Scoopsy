package com.example.scoopsy.data

data class ScoopsyUIState(
    var totalPrice: Double = 0.0,
    var isItemInCart: Boolean = false,
    var cartItems: List<CartItem> = listOf(),

    var currentItem: Item? = null,
    var currentQuantity: Int = 1,
    var currentType: IceCreamType = IceCreamTypes[0],
    var currentItemPrice: Double = 0.00,
    var reachedMinimumQuantity: Boolean = true,
    var reachedMaximumQuantity: Boolean = false,
    )

data class CartItem(var item: Item, var iceCreamType: IceCreamType?, var quantity: Int)