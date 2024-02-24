package com.example.scoopsy.ui

import androidx.lifecycle.ViewModel
import com.example.scoopsy.data.ScoopsyUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow



class ScoopsyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ScoopsyUIState())
    val uiState: StateFlow<ScoopsyUIState> = _uiState.asStateFlow()

}