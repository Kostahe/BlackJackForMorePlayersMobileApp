package com.example.blackjackformoreplayersmobileapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blackjackformoreplayersmobileapp.ui.BlackjackViewModel

enum class BlackjackScreen {
    MainMenu, Rules, InputAmountOfPlayers, InputNameOfPlayers, Game, Result
}

@Composable
fun BlackjackApp(
    viewModel: BlackjackViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
}

