package com.example.blackjackformoreplayersmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
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
                    color = MaterialTheme.colorScheme.background
                ) {
                    BlackJackApp()
                }
            }
        }
    }
}

@Composable
fun BlackJackApp() {
    var page by remember { mutableStateOf("Main menu") }
    fun setPage(string: String) {
        page = string
    }
    val loosePlayers : MutableList<Player> = mutableListOf()
    val firedPlayers : MutableList<Player> = mutableListOf()
    val winnerPlayer : MutableList<Player> = mutableListOf()
    val cardsList : MutableList<Card> = Card.generateCardList()
    when(page) {
        "Main menu" -> MainMenu()
        "Rules" -> Rules()
        "PlayersInput" -> PlayersInput()
        "Game" -> Game()
        "Result" -> Result()
    }
}

@Composable
fun MainMenu() {


}

@Composable
fun Rules() {

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