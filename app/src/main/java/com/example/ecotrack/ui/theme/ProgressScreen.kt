package com.example.ecotrack.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.math.roundToInt
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ProgressScreen(nav: NavController, vm: ChallengesViewModel) {

    val list by vm.challenges.collectAsState()

    val total = list.size
    val completed = list.count { it.completed }
    val progress = if (total == 0) 0f else completed.toFloat() / total.toFloat()
    val percent = (progress * 100f).roundToInt()

    Scaffold(
        topBar = {
            EcoTopBar(
                title = "Meu Progresso 🍃",
                onBack = { nav.navigate("home") }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Seu impacto sustentável",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Você concluiu $completed de $total desafios.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )

            // Gráfico circular de progresso
            ProgressRing(
                progress = progress,
                percent = percent
            )

            // Barra linear complementar
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            Text(
                text = when {
                    percent == 0 -> "Comece com um pequeno hábito hoje 🌱"
                    percent in 1..49 -> "Bom começo! Continue, o planeta agradece 💧"
                    percent in 50..99 -> "Você está indo muito bem, mantenha o ritmo 🌿"
                    else -> "Incrível! Você completou todos os desafios de hoje 🌎✨"
                },
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp),
            )

            Spacer(modifier = Modifier.weight(1f))

            BackToHomeButton(nav = nav)
        }
    }
}

@Composable
private fun ProgressRing(
    progress: Float,
    percent: Int,
    modifier: Modifier = Modifier.size(220.dp)
) {
    val clamped = progress.coerceIn(0f, 1f)

    val strokeWidth = 22f

    val trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
    val progressColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val sweep = 360f * clamped
            val stroke = strokeWidth

            // Fundo (trilho)
            drawArc(
                color = trackColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(stroke, cap = StrokeCap.Round),
                size = Size(size.width - stroke, size.height - stroke),
                topLeft = Offset(stroke / 2, stroke / 2)
            )

            // Progresso
            drawArc(
                color = progressColor,
                startAngle = -90f,
                sweepAngle = sweep,
                useCenter = false,
                style = Stroke(stroke, cap = StrokeCap.Round),
                size = Size(size.width - stroke, size.height - stroke),
                topLeft = Offset(stroke / 2, stroke / 2)
            )
        }

        // Texto central
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$percent%",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "concluído",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
