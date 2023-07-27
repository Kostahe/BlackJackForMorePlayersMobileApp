package com.example.blackjackformoreplayersmobileapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.blackjackformoreplayersmobileapp.model.Player
import com.example.blackjackformoreplayersmobileapp.ui.BlackjackViewModel
import com.example.blackjackformoreplayersmobileapp.ui.Game
import com.example.blackjackformoreplayersmobileapp.ui.InputAmountOfPlayer
import com.example.blackjackformoreplayersmobileapp.ui.InputNameOfPlayer
import com.example.blackjackformoreplayersmobileapp.ui.MainMenu
import com.example.blackjackformoreplayersmobileapp.ui.Result
import com.example.blackjackformoreplayersmobileapp.ui.Rules

enum class BlackjackScreen {
    MainMenu, Rules, InputAmountOfPlayers, InputNameOfPlayers, Game, Result
}

@Composable
fun BlackjackScreen(
    viewModel: BlackjackViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = BlackjackScreen.MainMenu.name,
        modifier = Modifier
    ) {
        composable(route = BlackjackScreen.MainMenu.name) {
            MainMenu(
                onStartButtonClick = { navController.navigate(BlackjackScreen.InputAmountOfPlayers.name) },
                onReadRulesButtonClick = { navController.navigate(BlackjackScreen.Rules.name) },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = BlackjackScreen.Rules.name) {
            Rules(
                onReturnButtonClick = { navController.navigate(BlackjackScreen.MainMenu.name) },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = BlackjackScreen.InputAmountOfPlayers.name) {
            InputAmountOfPlayer(
                value = uiState.amountOfPlayers,
                isValueWrong = {},
                onValueChange = { viewModel.setAmountOfPlayers(it) },
                onEnterButtonClick = {
                    if (uiState.amountOfPlayers.toIntOrNull() in 2..7) navController.navigate(
                        BlackjackScreen.InputNameOfPlayers.name
                    )
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = BlackjackScreen.InputNameOfPlayers.name) {
            InputNameOfPlayer(
                value = uiState.nameOfPlayer,
                isValueWrong = {},
                onValueChange = { viewModel.setNameOfPlayer(it) },
                onEnterButtonClick = {
                    if (uiState.nameOfPlayer.isNotBlank() && uiState.counterOfPlayers <= uiState.amountOfPlayers.toInt()) {
                        viewModel.updateCounterOfPlayer()
                        val player = Player(uiState.nameOfPlayer, uiState.counterOfPlayers)
                        viewModel.addPlayer(player)
                        viewModel.setNameOfPlayer("")
                    }
                    if (uiState.counterOfPlayers == uiState.amountOfPlayers.toInt() + 1) {
                        viewModel.updateCurrentPlayer()
                        navController.navigate(BlackjackScreen.Game.name)
                    }
                },
                playerList = uiState.playerList,
                counterOfPlayers = uiState.counterOfPlayers,
                amountOfPlayers = uiState.amountOfPlayers.toInt(),
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = BlackjackScreen.Game.name) {
            Game(
                currentPlayer = viewModel.getCurrentPlayer(),
                currentPlayerCardCollection = viewModel.getCurrentPlayerCardCollection(),
                currentPlayerSumaCards = viewModel.getCurrentPlayerSumaCards(),
                currentPlayerIndex = uiState.currentPlayerIndex,
                playerList = uiState.playerList,
                onHitButtonClick = { viewModel.hit() },
                nextPlayer = { viewModel.updateCurrentPlayer() },
                nextPage = {
                    if (uiState.loosePlayers.size < uiState.amountOfPlayers.toInt()) {
                        viewModel.determineWinner(uiState.playerList)
                        navController.navigate(BlackjackScreen.Result.name)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = BlackjackScreen.Result.name) {
            Result(
                winnerPlayers = uiState.winnerPlayer,
                loosePlayers = uiState.loosePlayers,
                onReturnButtonClick = {
                    viewModel.resetGame()
                    navController.popBackStack(BlackjackScreen.MainMenu.name, inclusive = false)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


