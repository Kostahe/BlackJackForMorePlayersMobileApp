package com.example.blackjackformoreplayersmobileapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.blackjackformoreplayersmobileapp.R
import com.example.blackjackformoreplayersmobileapp.model.Player
import com.example.blackjackformoreplayersmobileapp.ui.components.PlayerInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputNameOfPlayer(
    value: String,
    isValueWrong: Boolean,
    onValueChange: (String) -> Unit,
    onEnterButtonClick: () -> Unit,
    playerList: MutableList<Player>,
    counterOfPlayers: Int,
    amountOfPlayers: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        if (counterOfPlayers - 1 != amountOfPlayers) {
            TextField(
                value = value,
                label = {
                    if (!isValueWrong) Text(text = stringResource(id = R.string.input_name_of_player))
                    else Text(text = stringResource(R.string.name_cannot_be_empty))
                },
                onValueChange = onValueChange,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                isError = isValueWrong
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onEnterButtonClick() }) {
            Text(
                text = if (counterOfPlayers - 1 != amountOfPlayers) stringResource(id = R.string.enter) else stringResource(
                    id = R.string.start
                )
            )
        }
    }

    LazyRow {
        items(playerList) {
            PlayerInfo(player = it)
        }
    }
}