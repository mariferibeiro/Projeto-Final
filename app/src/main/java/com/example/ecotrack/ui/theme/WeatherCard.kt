package com.example.ecotrack.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun WeatherCard(
    temp: Double,
    condition: String,
    wind: Double
) {
    val icon = when (condition) {
        "Céu limpo" -> "☀️"
        "Parcialmente nublado" -> "⛅"
        "Chuva" -> "🌧️"
        "Chuvisco" -> "🌦️"
        "Tempestade" -> "⛈️"
        else -> "🌍"
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.elevatedCardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(icon, fontSize = MaterialTheme.typography.displayLarge.fontSize)
            Text(
                "${temp.toInt()}°C",
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(condition, style = MaterialTheme.typography.titleMedium)
            Text("Vento: $wind km/h", style = MaterialTheme.typography.bodyLarge)
        }
    }
}