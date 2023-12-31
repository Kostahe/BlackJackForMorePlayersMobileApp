package com.example.blackjackformoreplayersmobileapp.data

import androidx.compose.runtime.mutableStateListOf
import com.example.blackjackformoreplayersmobileapp.model.Card
import com.example.blackjackformoreplayersmobileapp.model.Player

data class BlackjackUiState(
    val amountOfPlayers: String = "",
    val playerList: MutableList<Player> = mutableStateListOf(),
    val cardsList: MutableList<Card> = Card.generateCardList(),
    val nameOfPlayer: String = "",
    val counterOfPlayers: Int = 1,
    val currentPlayerIndex: Int = 0,
    val loosePlayers: MutableList<Player> = mutableListOf(),
    val firedPlayers: MutableList<Player> = mutableListOf(),
    val winnerPlayer: MutableList<Player> = mutableListOf(),
    val isAmountOfPlayersWrong: Boolean = false,
    val isNameOfPlayerWrong: Boolean = false
)
