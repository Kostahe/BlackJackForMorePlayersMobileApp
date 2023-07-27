package com.example.blackjackformoreplayersmobileapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.blackjackformoreplayersmobileapp.data.BlackjackUiState
import com.example.blackjackformoreplayersmobileapp.model.Card
import com.example.blackjackformoreplayersmobileapp.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BlackjackViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BlackjackUiState())
    val uiState: StateFlow<BlackjackUiState> = _uiState.asStateFlow()

    private lateinit var _currentPlayer: Player
    private var _currentPlayerCardCollection = mutableStateListOf<Card>()
    private var _currentPlayerSumValueCards by mutableStateOf(0)

    private val cardsList = Card.generateCardList()

    fun setAmountOfPlayers(_amountOfPlayers: String) {
        _uiState.update { currentState ->
            currentState.copy(amountOfPlayers = _amountOfPlayers)
        }
    }

    fun setNameOfPlayer(_nameOfPlayer: String) {
        _uiState.update { currentState ->
            currentState.copy(nameOfPlayer = _nameOfPlayer)
        }
    }

    fun addPlayer(player: Player) {
        _uiState.value.playerList.add(player)
    }

    fun updateCounterOfPlayer() {
        _uiState.update { currentState ->
            currentState.copy(
                counterOfPlayers = _uiState.value.counterOfPlayers.inc()
            )
        }
    }

    fun updateCurrentPlayer() {
        _currentPlayer = uiState.value.playerList[_uiState.value.currentPlayerIndex]
        _currentPlayerCardCollection.retainAll(_currentPlayer.cardCollection)
        _currentPlayerSumValueCards = 0
        _uiState.update { currentState ->
            currentState.copy(
                currentPlayerIndex = uiState.value.currentPlayerIndex.inc(),
            )
        }
    }

    fun getCurrentPlayer() = _currentPlayer
    fun getCurrentPlayerCardCollection() = _currentPlayerCardCollection
    fun getCurrentPlayerSumaCards() = _currentPlayerSumValueCards

    fun hit() {
        _currentPlayer.takeCard(cardsList)
        _currentPlayerCardCollection.add(_currentPlayer.cardCollection.last())
        _currentPlayerSumValueCards = _currentPlayer.sumValueCards
    }
}

