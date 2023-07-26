package com.example.blackjackformoreplayersmobileapp.data

import androidx.compose.runtime.mutableStateListOf
import com.example.blackjackformoreplayersmobileapp.model.Card
import com.example.blackjackformoreplayersmobileapp.model.Player

data class BlackjackUiState(
    val amountOfPlayers: String = "",
    val playerList: MutableList<Player> = mutableStateListOf<Player>(),
    val cardsList: List<Card> = Card.generateCardList(),
    val nameOfPlayer: String = "",
    val counter: Int = 0,
)
