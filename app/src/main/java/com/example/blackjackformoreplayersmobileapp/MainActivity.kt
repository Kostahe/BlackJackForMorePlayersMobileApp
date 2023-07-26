package com.example.blackjackformoreplayersmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blackjackformoreplayersmobileapp.model.Player
import com.example.blackjackformoreplayersmobileapp.ui.theme.BlackJackForMorePlayersMobileAppTheme
import kotlin.system.exitProcess
import com.example.blackjackformoreplayersmobileapp.model.Card as CardClass


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlackJackForMorePlayersMobileAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    BlackJackApp()
                }
            }
        }
    }
}

@Composable
fun BlackJackApp() {
    var page by remember { mutableStateOf("MainMenu") }
    Box {
        Image(painter = painterResource(id = R.drawable.black_jack_menu2), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxHeight())
        when(page) {
            "MainMenu" -> MainMenu({ page = "Game" }, { page = "Rules" })
            "Rules" -> Rules({ page = "MainMenu" })
            "Game" -> Game { page = "MainMenu" }
        }
    }
}

@Composable
fun MainMenu(
    onStartButtonClick: () -> Unit,
    onReadRulesClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
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
            onClick = { onStartButtonClick() },
            modifier = Modifier
                .width(250.dp)
                .padding(top = 40.dp),
        ) {
            Text(
                text = stringResource(R.string.start),
            )
        }
        Button(
            onClick = { onReadRulesClick() },
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

@Composable
fun Rules(
    returnToMainMenu: () -> Unit,
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
            onClick = { returnToMainMenu() },
            modifier = Modifier.padding(start = 20.dp, top = 50.dp),
            ) {
                Text(text = stringResource(R.string.return_to_main_menu))
        }
    }
}

@Composable
fun Game(
    returnToMainMenu: () -> Unit
) {
    var gameState by remember { mutableStateOf("Input amount") }
    var amountOfPlayers by remember { mutableStateOf("") }
    val playerList = remember { mutableStateListOf<Player>() }
    val cardsList = CardClass.generateCardList()
    var nameOfPlayer by remember { mutableStateOf("") }
    var counter by remember { mutableStateOf(0) }

    when (gameState) {

        "Input amount" -> {

            TextFieldWithButton(
                textFieldLabel = R.string.input_amount_of_players,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                value = amountOfPlayers,
                onValueChange = {amountOfPlayers = it},
                onButtonClick = { if(amountOfPlayers.toIntOrNull() in 2..7) gameState = "Input name"  }
            )
        }
            "Input name" -> {
                TextFieldWithButton(
                    textFieldLabel = R.string.input_name_of_player,
                    textButton = if (counter < amountOfPlayers.toInt()) R.string.enter else R.string.start,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    value = nameOfPlayer,
                    onValueChange = { nameOfPlayer = it },
                    onButtonClick = {
                        if (counter == amountOfPlayers.toInt()) {
                            counter++
                        }
                        if (nameOfPlayer.isNotBlank() && counter < amountOfPlayers.toInt()) {
                            counter++
                            val player =
                                Player(
                                    nameOfPlayer,
                                    counter
                                )
                            playerList.add(player)
                            nameOfPlayer = ""
                        }
                        if (counter == amountOfPlayers.toInt() + 1) {
                            gameState = "Game"
                        }
                    }
                )
                LazyRow {
                    items(playerList) {
                        PlayerInfo(player = it)
                    }
                }
            }
        "Game" -> {
            var currentPlayerIndex by remember { mutableStateOf(0) }
            var currentPlayer by remember{ mutableStateOf(playerList[currentPlayerIndex]) }
            val currentPlayerCardCollection = remember { mutableStateListOf<CardClass>() }
            var currentPlayerSumaCards by remember { mutableStateOf(currentPlayer.sumaValueCards) }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = currentPlayer.name + " now plays!")
                Text(text = stringResource(R.string.your_cards_are))

                LazyColumn {
                    items(currentPlayerCardCollection) { card ->
                        Text(text = card.toString())
                    }
                }
                Text(text = "Suma is: " + currentPlayer.sumaValueCards)
                if (currentPlayerSumaCards <= 21) {
                    Row(modifier = Modifier.width(200.dp)) {
                        Button(
                            onClick = {
                            currentPlayer.takeCard(cardsList)
                            currentPlayerCardCollection.add(currentPlayer.cardCollection.last())
                            currentPlayerSumaCards = currentPlayer.sumaValueCards
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp)
                        ) {
                            Text(text = stringResource(R.string.hit))
                        }
                        Button(onClick = {

                            if(currentPlayerIndex + 1 < playerList.size) {
                                currentPlayerIndex++
                                currentPlayer = playerList[currentPlayerIndex]
                                currentPlayerSumaCards = 0
                                currentPlayerCardCollection.clear()
                            } else  {
                                gameState = "Result"
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 5.dp)
                        ) {
                            Text(text = stringResource(R.string.stand))
                        }
                    }
                }
                else {
                    if(currentPlayerIndex + 1 < playerList.size) {
                        currentPlayerIndex++
                        currentPlayer = playerList[currentPlayerIndex]
                        currentPlayerSumaCards = 0
                        currentPlayerCardCollection.clear()
                    } else  {
                        gameState = "Result"
                    }
                }
            }
        }
        "Result" -> {
            val loosePlayers = mutableListOf<Player>()
            val firedPlayers = mutableListOf<Player>()
            val winnerPlayers = mutableListOf<Player>()

            playerList.forEach { player ->
                if (player.sumaValueCards > 21)
                    firedPlayers.add(player)
                else
                    winnerPlayers.add(player)
            }
            if (winnerPlayers.size > 0) {
                val maxPointsOfPlayer: Int = winnerPlayers.max().sumaValueCards
                winnerPlayers.forEach { player ->
                    if (player.sumaValueCards != maxPointsOfPlayer)
                        loosePlayers.add(player)
                }
            }
            loosePlayers.sortDescending()
            loosePlayers.addAll(firedPlayers)
            winnerPlayers.removeAll(loosePlayers)

            Column(
                modifier = Modifier
                    .fillMaxSize(),
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
                        onClick = returnToMainMenu,
                    ) {
                        Text(text = stringResource(id = R.string.return_to_main_menu))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithButton(
    @StringRes textFieldLabel: Int,
    @StringRes textButton: Int = R.string.enter,
    keyboardOptions: KeyboardOptions,
    value: String?,
    onValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if(textButton != R.string.start) {
            TextField(
                value = value!!,
                label = { Text(text = stringResource(id = textFieldLabel)) },
                onValueChange = onValueChange,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = keyboardOptions
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = onButtonClick) {
            Text(text = stringResource(textButton))
        }
    }
}

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
                text = "Player${player.id}: ${player.name}",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = player.sumaValueCards.toString(),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(top = 4.dp, end = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlackJackAppPreview() {
    BlackJackForMorePlayersMobileAppTheme {
        BlackJackApp()
    }
}