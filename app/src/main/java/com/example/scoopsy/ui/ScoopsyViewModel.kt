package com.example.scoopsy.ui

import androidx.lifecycle.ViewModel
import com.example.scoopsy.data.IceCreamType
import com.example.scoopsy.data.Item
import com.example.scoopsy.data.ScoopsyUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


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
        if (currentQuantity == 9) {
            _uiState.update { currentState ->
                currentState.copy(reachedMaximumQuantity = true)
            }
        }
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

    private fun calculateItemPrice() {
        val uiState = _uiState.value
        val itemPrice =
            (uiState.currentItem.price + uiState.currentType.price) * (uiState.currentQuantity)

        _uiState.update { currentState ->
            currentState.copy(
                currentItemPrice = itemPrice
            )
        }
    }

    private fun resetCurrentItem() {
        val defaultState = ScoopsyUIState()
        _uiState.update {
            defaultState

        }
    }

}