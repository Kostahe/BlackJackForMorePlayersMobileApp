@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.core.view.WindowCompat
import com.example.blackjackformoreplayersmobileapp.ui.theme.BlackJackForMorePlayersMobileAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val activityKiller: () -> Unit = {this.finish()}
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
    fun setPage(string: String) {
        page = string
    }
    Box {
        Image(painter = painterResource(id = R.drawable.black_jack_menu2), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxHeight())
        when(page) {
            "MainMenu" -> MainMenu({page = "Game"}, {page = "Rules"}, {})
            "Rules" -> Rules({ page = "MainMenu" })
            "Game" -> Game()
            "Result" -> Result()
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
        modifier = Modifier.fillMaxSize() // delete this later
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
            colors = ButtonDefaults.buttonColors(Color(203, 45, 115))
        ) {
            Text(
                text = stringResource(R.string.start),
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = { onReadRulesClick() },
            modifier = Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(Color(53, 61, 108))
        ) {
            Text(
                text = stringResource(R.string.read_the_rules),
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = { onEndClick() },
            modifier = Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(Color(53, 61, 108))
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
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
        ) {
        Text(
            text = "The rules",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 50.sp,
            modifier = Modifier.padding(end = 32.dp, start = 19.dp)
            )
        Text(
            text = "1. Black jack players are scored by their total cards value. The player with the highest total cards value wins as long as it doesn't exceed 21. The players with a highest totals than 21 are said to bust.\n\n2. Amount of players that can play is between 2-7.\n\n3. Ace is 11.",
            modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 25.dp),
            textAlign = TextAlign.Justify,
        )
        Button(
            onClick = { returnToMainMenu() },
            modifier = Modifier.padding(start = 20.dp, top = 50.dp),
            ) {
                Text(text = "Return to main menu")
        }

    }


}


@Composable
fun Game() {
    var gameState by remember { mutableStateOf("Input amount") }
    val playerList = remember { mutableStateListOf<Player>() }
    var amountOfPlayers by remember { mutableStateOf("") }
    var nameOfPlayer by remember { mutableStateOf("") }
    var counter by remember { mutableStateOf(0) }

    when (gameState) {
        "Input amount" ->
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
                        val player = Player(nameOfPlayer, counter)
                        playerList.add(player)
                        nameOfPlayer = ""
                    }
                    if (counter == amountOfPlayers.toInt() + 1) {
                        gameState = "Game"
                    }
                }
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                playerList.forEach { player ->
                    Text(text = "Player${player.id} : ${player.name}")
                }
            }
        }
        "Game" -> {
            val loosePlayersList = remember { mutableStateListOf<Player>() }
            val firedPlayersList = remember { mutableStateListOf<Player>() }
            val winnerPlayersList = remember { mutableStateListOf<Player>() }
            val cardsList = Card.generateCardList()
            var playerCount by remember { mutableStateOf(0) }
            var player by remember(playerCount) { mutableStateOf(playerList[playerCount]) }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(text = player.name + " now plays!")
                Text(text = "Your cards are: ")

                player.cardCollection.forEach() { card ->
                    Text(text = card.toString())
                }
                Text(text = "Suma is: " + player.sumaValueCards)
                if (player.sumaValueCards <= 21) {
                    Row {
                        Button(onClick = {
                            player.takeCard(cardsList)
                        }) {
                            Text(text = "Hit")
                        }
                        Button(onClick = {
                            playerCount++
                            player = playerList[playerCount]
                        }
                        ) {
//                            Text(text = )
                        }
                        Button(onClick = { /*TODO*/ }) {

                        }
                    }

                }

            }






        }
    }
}






@Composable
fun Result() {

}

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

@Preview(showBackground = true)
@Composable
fun BlackJackAppPreview(modifier: Modifier = Modifier.fillMaxSize()) {
    BlackJackApp()
}