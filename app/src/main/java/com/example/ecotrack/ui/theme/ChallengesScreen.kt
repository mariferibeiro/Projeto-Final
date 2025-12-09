package com.example.ecotrack.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ChallengesScreen(nav: NavController, vm: ChallengesViewModel) {

    val list by vm.challenges.collectAsState()

    val total = list.size
    val completed = list.count { it.completed }
    val progress = if (total == 0) 0f else completed.toFloat() / total.toFloat()

    Scaffold(
        topBar = {
            EcoTopBar(
                title = "Desafios Diários 🌿",
                onBack = { nav.navigate("home") }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Resumo do progresso dos desafios
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Resumo de hoje",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Você concluiu $completed de $total desafios.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = progress,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )
                }
            }

            // Lista de desafios
            if (list.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nenhum desafio cadastrado ainda.\nAdicione hábitos sustentáveis para começar 🌱",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(list) { challenge ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = challenge.text,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Checkbox(
                                    checked = challenge.completed,
                                    onCheckedChange = { vm.toggle(challenge.id) }
                                )
                            }
                        }
                    }
                }
            }

            // Botão para voltar à home (opcional, além do back da AppBar)
            BackToHomeButton(nav = nav)
        }
    }
}
