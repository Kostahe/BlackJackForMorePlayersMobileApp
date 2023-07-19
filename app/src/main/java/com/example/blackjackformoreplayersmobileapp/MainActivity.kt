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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blackjackformoreplayersmobileapp.classes.Player
import com.example.blackjackformoreplayersmobileapp.classes.Card as CardClass
import com.example.blackjackformoreplayersmobileapp.ui.theme.BlackJackForMorePlayersMobileAppTheme


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
            "MainMenu" -> MainMenu({page = "Game"}, {page = "Rules"}, {})
            "Rules" -> Rules({ page = "MainMenu" })
            "Game" -> Game { page = "Result" }
        }
    }
}

@Composable
fun MainMenu(
    onStartButtonClick: () -> Unit,
    onReadRulesClick: () -> Unit,
    onEndClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.blackjack_title),
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 70.sp,
        )
        Button(
            onClick = { onStartButtonClick() },
            modifier = Modifier
                .width(250.dp)
                .padding(top = 40.dp),
//            colors = ButtonDefaults.buttonColors(Color(203, 45, 115))
        ) {
            Text(
                text = stringResource(R.string.start),
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = { onReadRulesClick() },
            modifier = Modifier.width(250.dp),
//            colors = ButtonDefaults.buttonColors(Color(53, 61, 108))
        ) {
            Text(
                text = stringResource(R.string.read_the_rules),
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = { onEndClick() },
            modifier = Modifier.width(250.dp),
//            colors = ButtonDefaults.buttonColors(Color(53, 61, 108))
        ) {
            Text(
                text = stringResource(R.string.end),
                fontWeight = FontWeight.Bold
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
        Text(
            text = stringResource(R.string.the_rules),
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 50.sp,
            modifier = Modifier.padding(end = 32.dp, start = 19.dp)
            )
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
fun Game(onResult: () -> Unit) {
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
                onButtonClick = { if(amountOfPlayers.toInt() in 2..7) gameState = "Input name"  }
            )
        }
            "Input name" -> {

                TextFieldWithButton(
                    textFieldLabel = R.string.input_name_of_player,
                    textButton = if(counter < amountOfPlayers.toInt()) R.string.enter else R.string.start ,
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
                // test if works it won't be included in final version of game or I will just do it normal
                PlayersInfo(playerList)
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
                            currentPlayer.cardCollection.clear()

                            if(currentPlayerIndex + 1 < playerList.size) {
                                currentPlayerIndex++
                                currentPlayer = playerList[currentPlayerIndex]
                                currentPlayerSumaCards = 0
                                currentPlayerCardCollection.clear()
                            } else  {
                                onResult()
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
                        onResult()
                    }
                }
            }
        }
        "Result" -> {
            val loosePlayers = mutableListOf<Player>()
            val firedPlayers = mutableListOf<Player>()
            val winnerPlayers = mutableListOf<Player>()


            playerList.forEach { player ->
                if(player.sumaValueCards > 21)
                    firedPlayers.add(player)
                else
                    winnerPlayers.add(player)
            }
            val maxPointsOfPlayer: Int = winnerPlayers.max().sumaValueCards
            playerList.forEach { player ->
                if (player.sumaValueCards != maxPointsOfPlayer)
                    loosePlayers.add(player)
            }
            loosePlayers.sortDescending()
            loosePlayers.addAll(firedPlayers)
            winnerPlayers.removeAll(loosePlayers)
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
        TextField(
            value = value!!,
            label = { Text(text = stringResource(id = textFieldLabel))},
            onValueChange =  onValueChange,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = keyboardOptions
            )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onButtonClick) {
            Text(text = stringResource(textButton))
        }
    }
}
@Composable
fun PlayersInfo(
    playersList: List<Player>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(playersList) { player ->
            PlayerInfo(player)
        }
    }
}

@Composable
fun PlayerInfo(
    player: Player,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {
            Text(text = "Player${player.id} : ${player.name}")
            Image(
                painter = painterResource(id = R.drawable.anonymous_user),
                contentDescription = null,
                modifier = Modifier
                    .width(62.dp)
                    .height(62.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlackJackAppPreview() {
    BlackJackApp()
}