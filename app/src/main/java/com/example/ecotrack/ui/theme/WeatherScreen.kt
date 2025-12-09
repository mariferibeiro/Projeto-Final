package com.example.ecotrack.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun WeatherScreen(nav: NavController, vm: HomeViewModel) {

    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) { vm.fetchWeather() }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        // Topo da tela (SEM TopAppBar problemático)
        EcoTopBar(title = "Buscar Clima", onBack = { nav.navigate("home") })

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            when {
                state.loading -> {
                    CircularProgressIndicator()
                    Text("Obtendo informações do clima...")
                }

                state.error != null -> {
                    Text(
                        text = state.error!!,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold
                    )
                    Button(onClick = { vm.fetchWeather() }) {
                        Text("Tentar novamente")
                    }
                }

                else -> {
                    WeatherCard(
                        temp = state.temperature ?: 0.0,
                        condition = state.condition ?: "Indefinido",
                        wind = state.wind ?: 0.0
                    )

                    Button(
                        onClick = { vm.fetchWeather() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Atualizar Clima")
                    }
                }
            }
        }
    }
}