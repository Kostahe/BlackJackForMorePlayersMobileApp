package com.example.blackjackformoreplayersmobileapp.ui

import androidx.lifecycle.ViewModel
import com.example.blackjackformoreplayersmobileapp.data.BlackjackUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BlackjackViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BlackjackUiState())
    val uiState: StateFlow<BlackjackUiState> = _uiState.asStateFlow()
}