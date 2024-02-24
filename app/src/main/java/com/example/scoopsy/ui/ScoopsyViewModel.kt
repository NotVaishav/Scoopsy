package com.example.scoopsy.ui

import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
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
        _uiState.update { currentState ->
            currentState.copy(
                currentItem = item
            )
        }
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
    }

}