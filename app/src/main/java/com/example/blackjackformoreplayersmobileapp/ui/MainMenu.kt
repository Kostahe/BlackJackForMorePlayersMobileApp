package com.example.blackjackformoreplayersmobileapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.blackjackformoreplayersmobileapp.MainActivity
import com.example.blackjackformoreplayersmobileapp.R
import kotlin.system.exitProcess

@Composable
fun MainMenu(
    onStartButtonClick: () -> Unit,
    onReadRulesButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.playing_cards_48px),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(50.dp)
            )
            Text(
                text = stringResource(R.string.blackjack_title),
                style = MaterialTheme.typography.displayLarge,
            )
        }

        Button(
            onClick = onStartButtonClick,
            modifier = Modifier
                .width(250.dp)
                .padding(top = 40.dp),
        ) {
            Text(
                text = stringResource(R.string.start),
            )
        }
        Button(
            onClick = onReadRulesButtonClick,
            modifier = Modifier.width(250.dp),
        ) {
            Text(
                text = stringResource(R.string.read_the_rules),
            )
        }
        Button(
            onClick = {
                val activity = MainActivity()
                activity.finish()
                exitProcess(0)
            },
            modifier = Modifier.width(250.dp),
        ) {
            Text(
                text = stringResource(R.string.end),
            )
        }
    }
}