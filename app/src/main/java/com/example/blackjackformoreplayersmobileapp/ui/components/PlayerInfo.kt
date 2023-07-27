package com.example.blackjackformoreplayersmobileapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blackjackformoreplayersmobileapp.R
import com.example.blackjackformoreplayersmobileapp.model.Player

@Composable
fun PlayerInfo(
    player: Player,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(4.dp)) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.anonymous_user),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp)
                    .clip(MaterialTheme.shapes.small),
            )
            Text(
                text = "Player${player.id}: ${player.name}",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}