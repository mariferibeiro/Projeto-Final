package com.example.ecotrack.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph() {

    val nav = rememberNavController()

    // ViewModels compartilhados entre as telas
    val challengesVM: ChallengesViewModel = viewModel()
    val addVM: AddViewModel = viewModel()
    val homeVM: HomeViewModel = viewModel()     // usado pelo WeatherScreen

    NavHost(
        navController = nav,
        startDestination = "login"   // começa na tela de login
    ) {

        // TELA DE LOGIN
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    nav.navigate("home") {
                        popUpTo("login") { inclusive = true } // remove login da pilha
                    }
                }
            )
        }

        // HOME
        composable("home") {
            HomeScreen(nav = nav)
        }

        // ADICIONAR HÁBITO
        composable("add") {
            AddScreen(
                vm = addVM,
                onSave = { text ->
                    // Aqui você pode integrar com ChallengesViewModel depois
                    // Ex: adicionar um Challenge novo
                    // Por enquanto, só navega para a tela de desafios
                    nav.navigate("desafios")
                }
            )
        }

        // DESAFIOS
        composable("desafios") {
            ChallengesScreen(
                nav = nav,
                vm = challengesVM
            )
        }

        // PROGRESSO
        composable("progresso") {
            ProgressScreen(
                nav = nav,
                vm = challengesVM
            )
        }

        // CLIMA
        composable("clima") {
            WeatherScreen(
                nav = nav,
                vm = homeVM      // WeatherScreen espera HomeViewModel
            )
        }
    }
}
