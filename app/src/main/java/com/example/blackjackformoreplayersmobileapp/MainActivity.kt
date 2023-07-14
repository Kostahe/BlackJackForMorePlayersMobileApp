package com.example.blackjackformoreplayersmobileapp

import android.icu.number.Scale
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
    val loosePlayers : MutableList<Player> = mutableListOf()
    val firedPlayers : MutableList<Player> = mutableListOf()
    val winnerPlayer : MutableList<Player> = mutableListOf()
    val cardsList : MutableList<Card> = Card.generateCardList()
    Box() {
        Image(painter = painterResource(id = R.drawable.black_jack_menu2), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxHeight())
        when(page) {
            "MainMenu" -> MainMenu({page = "PlayersInput"}, {page = "Rules"}, {})
            "Rules" -> Rules({ page = "MainMenu" })
            "PlayersInput" -> PlayersInput()
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
    Button(
        onClick = { returnToMainMenu() },
        modifier = Modifier.padding(start = 20.dp, top = 50.dp),

    ) {
        Text(
            text = "Return to main menu",
            )
    }
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
    }
}

@Composable
fun PlayersInput() {

}
@Composable
fun Game() {

}
@Composable
fun Result() {

}




@Preview(showBackground = true)
@Composable
fun BlackJackAppPreview(modifier: Modifier = Modifier.fillMaxSize()) {
    BlackJackApp()
}