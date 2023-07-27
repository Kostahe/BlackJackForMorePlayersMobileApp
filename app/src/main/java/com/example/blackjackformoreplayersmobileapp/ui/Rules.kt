package com.example.blackjackformoreplayersmobileapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blackjackformoreplayersmobileapp.R

@Composable
fun Rules(
    onReturnButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.developer_guide_48px),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(50.dp)
            )
            Text(
                text = stringResource(R.string.the_rules),
                style = MaterialTheme.typography.displayLarge,
            )
        }
        Text(
            text = stringResource(R.string.rules),
            modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 25.dp),
            textAlign = TextAlign.Justify,
        )
        Button(
            onClick = onReturnButtonClick,
            modifier = Modifier.padding(start = 20.dp, top = 50.dp),
        ) {
            Text(text = stringResource(R.string.return_to_main_menu))
        }
    }
}