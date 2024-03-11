package com.example.scoopsy.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scoopsy.data.CartItem
import com.example.scoopsy.data.IceCreamType
import com.example.scoopsy.data.IceCreamTypes
import com.example.scoopsy.data.Item
import com.example.scoopsy.data.Items
import com.example.scoopsy.data.ScoopsyUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ScoopsyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ScoopsyUIState())
    val uiState: StateFlow<ScoopsyUIState> = _uiState.asStateFlow()

    fun setCurrentItem(item: Item) {
        resetCurrentItem()
        _uiState.update { currentState ->
            currentState.copy(
                currentItem = item
            )
        }
        calculateItemPrice()
    }

    fun increaseQuantity() {
        val currentQuantity = _uiState.value.currentQuantity
//        if (currentQuantity == 9) {
//            _uiState.update { currentState ->
//                currentState.copy(reachedMaximumQuantity = true)
//            }
//        }
        _uiState.update { currentState ->
            currentState.copy(
                currentQuantity = currentQuantity + 1,
                reachedMinimumQuantity = false
            )
        }
        calculateItemPrice()
    }

    fun decreaseQuantity() {
        val currentQuantity = _uiState.value.currentQuantity
        if (currentQuantity == 2) {
            _uiState.update { currentState ->
                currentState.copy(reachedMinimumQuantity = true)
            }
        }
        _uiState.update { currentState ->
            currentState.copy(
                currentQuantity = currentQuantity - 1,
                reachedMaximumQuantity = false
            )
        }
        calculateItemPrice()
    }

    fun selectCurrentType(type: IceCreamType) {
        _uiState.update { currentState ->
            currentState.copy(
                currentType = type,
            )
        }
        calculateItemPrice()
    }

    fun addItemToCart(
        item: Item,
        iceCreamType: IceCreamType? = null,
        quantity: Int,
    ) {
        val existingCartItem =
            _uiState.value.cartItems.find { (it.item == item) && (it.iceCreamType == iceCreamType) }
        val iceCreamTypePrice = iceCreamType?.price ?: 0.00

        _uiState.update { currentState ->
            val updatedCartItems = if (existingCartItem != null) {
                currentState.cartItems.map { cartItem ->
                    if (cartItem.item == item) {
                        cartItem.copy(quantity = cartItem.quantity + quantity)
                    } else {
                        cartItem
                    }
                }
            } else {
                currentState.cartItems + CartItem(
                    item,
                    quantity = quantity,
                    iceCreamType = iceCreamType,
                    eachItemPrice = (item.price + iceCreamTypePrice)
                )
            }
            currentState.copy(cartItems = updatedCartItems)
        }
        calculateTotalPrice()
    }

    fun increaseCartItemQuantity(item: CartItem) {
        _uiState.update { currentState ->
            val updatedCartItems = currentState.cartItems.map { cartItem ->
                if (cartItem == item) {
                    cartItem.copy(quantity = cartItem.quantity + 1)
                } else {
                    cartItem
                }
            }
            currentState.copy(cartItems = updatedCartItems)
        }
        calculateTotalPrice()
    }

    fun decreaseCartItemQuantity(item: CartItem) {
        _uiState.update { currentState ->
            val updatedCartItems = currentState.cartItems.map { cartItem ->
                if (cartItem == item) {
                    cartItem.copy(quantity = cartItem.quantity - 1)
                } else {
                    cartItem
                }
            }
            currentState.copy(cartItems = updatedCartItems)
        }
        calculateTotalPrice()
    }

    fun deleteItemFromCart(item: CartItem) {
        _uiState.update { currentState ->
            val updatedCartItems = currentState.cartItems.filterNot { it == item }
            currentState.copy(cartItems = updatedCartItems)
        }
        calculateTotalPrice()
    }

    fun setBottomSheetValue(value: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(showBottomSheet = value)
        }
    }

    fun setDialogValue(value: Boolean) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(showDialog = value)
            }
        }
    }


    private fun calculateItemPrice() {
        val uiState = _uiState.value
        val typePrice: Double = uiState.currentType?.price ?: 0.00
        val itemPrice =
            (uiState.currentItem.price + typePrice) * uiState.currentQuantity
        _uiState.update { currentState ->
            currentState.copy(
                currentItemPrice = itemPrice
            )
        }
        calculateTotalPrice()
    }

    private fun resetCurrentItem() {
        _uiState.update { currentState ->
            currentState.copy(
                currentItem = Items[0],
                currentQuantity = 1,
                currentType = IceCreamTypes[0],
                currentItemPrice = 0.00,
                reachedMinimumQuantity = true,
                reachedMaximumQuantity = false,
            )
        }
    }

    fun resetApp() {
        val defaultState = ScoopsyUIState()
        _uiState.update {
            defaultState
        }
    }

    private fun calculateTotalPrice() {
        val uiState = _uiState.value
        val currentItems = uiState.cartItems
        var totalPrice = 0.0
        for (item in currentItems) {
            totalPrice += item.eachItemPrice * item.quantity
        }
        _uiState.update { currentState ->
            currentState.copy(
                totalPrice = totalPrice
            )
        }
    }

}