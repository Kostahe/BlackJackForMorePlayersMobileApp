package com.example.blackjackformoreplayersmobileapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.blackjackformoreplayersmobileapp.R
import com.example.blackjackformoreplayersmobileapp.model.Card
import com.example.blackjackformoreplayersmobileapp.model.Player

@Composable
fun Game(
    currentPlayer: Player,
    currentPlayerCardCollection: MutableList<Card>,
    currentPlayerSumaCards: Int,
    cardsList: MutableList<Card>,
    currentPlayerIndex: Int,
    playerList: MutableList<Player>,
    onHitButtonClick: () -> Unit,
    nextPlayer: () -> Unit,
    nextPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(text = currentPlayer.name + " now plays!")
        Text(text = stringResource(R.string.your_cards_are))

        LazyRow {
            items(currentPlayerCardCollection) { card ->
                Image(painter = painterResource(id = card.cardImage), contentDescription = null)
            }
        }
        Text(text = "Suma is: " + currentPlayer.sumValueCards)
        if (currentPlayerSumaCards <= 21) {
            Row(modifier = Modifier.width(200.dp)) {
                Button(
                    onClick = {
                        onHitButtonClick()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp)
                ) {
                    Text(text = stringResource(R.string.hit))
                }
                Button(
                    onClick = {
                        if (currentPlayerIndex + 1 < playerList.size) nextPlayer()
                        else nextPage()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.dp)
                ) {
                    Text(text = stringResource(R.string.stand))
                }
            }
        } else {
            if (currentPlayerIndex + 1 < playerList.size) {
                nextPlayer()
            } else {
                nextPage()
            }
        }
    }
}
