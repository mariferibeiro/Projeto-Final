package com.example.ecotrack.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(nav: NavController) {

    Scaffold(
        topBar = {
            // Barra superior simples com o tema ecológico
            EcoTopBar(title = "EcoTrack 🌱")
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "Bem-vindo(a) de volta 👋",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Acompanhe seus hábitos sustentáveis e veja o impacto das suas ações no planeta.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Card de destaque
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Seu dia mais verde 🌿",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Cumprindo pequenos desafios diários, você constrói um estilo de vida mais sustentável.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botões principais
            Button(
                onClick = { nav.navigate("desafios") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Desafios Diários")
            }

            Button(
                onClick = { nav.navigate("progresso") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Meu Progresso")
            }

            Button(
                onClick = { nav.navigate("clima") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Buscar Clima")
            }
        }
    }
}
