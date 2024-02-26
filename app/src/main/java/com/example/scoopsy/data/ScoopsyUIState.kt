package com.example.scoopsy.data

data class ScoopsyUIState(
    var totalPrice: Double = 0.0,
    var isItemInCart: Boolean = false,
    var cartItems: List<CartItem> = listOf(
//        CartItem(item = Items[0], quantity = 1, eachItemPrice = 12.99),
//        CartItem(
//            item = normalItems[0],
//            iceCreamType = IceCreamTypes[1],
//            quantity = 2,
//            eachItemPrice = 4.99
//        )
    ),

    var currentItem: Item = Items[0],
    var currentQuantity: Int = 1,
    var currentType: IceCreamType? = IceCreamTypes[0],
    var currentItemPrice: Double = 0.00,
    var reachedMinimumQuantity: Boolean = true,
    var reachedMaximumQuantity: Boolean = false,
)

data class CartItem(
    var item: Item,
    var iceCreamType: IceCreamType? = null,
    var quantity: Int,
    var eachItemPrice: Double
)