package com.example.blackjackformoreplayersmobileapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.blackjackformoreplayersmobileapp.R
import com.example.blackjackformoreplayersmobileapp.model.Player
import com.example.blackjackformoreplayersmobileapp.ui.components.PlayerInfoScore

@Composable
fun Result(
    winnerPlayers: MutableList<Player>,
    loosePlayers: MutableList<Player>,
    onReturnButtonClick: () -> Unit,
    modifier: Modifier

) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.menu_48px),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(50.dp)
            )
            Text(
                text = stringResource(R.string.score_board),
                style = MaterialTheme.typography.displayLarge
            )
        }
        LazyColumn {
            items(winnerPlayers) {
                Row {
                    PlayerInfoScore(player = it, color = Color(254, 192, 9))
                }
            }
        }
        LazyColumn {
            items(loosePlayers) {
                Row {
                    PlayerInfoScore(player = it)
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onReturnButtonClick,
            ) {
                Text(text = stringResource(id = R.string.return_to_main_menu))
            }
        }
    }
}
