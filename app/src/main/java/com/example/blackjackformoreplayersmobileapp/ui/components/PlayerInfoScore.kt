package com.example.blackjackformoreplayersmobileapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.blackjackformoreplayersmobileapp.R
import com.example.blackjackformoreplayersmobileapp.model.Player

@Composable
fun PlayerInfoScore(
    player: Player,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    Card(
        modifier = modifier.padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = color,
        )
    ) {
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
                text = stringResource(R.string.player_playerId_playerName, player.id, player.name),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            LazyRow {
                items(player.cardCollection) { card ->
                    Image(
                        painter = painterResource(id = card.cardImage),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Text(
                text = player.sumValueCards.toString(),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp, end = 8.dp)
            )
        }
    }
}