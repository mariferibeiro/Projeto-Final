package com.example.ecotrack.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BackToHomeButton(nav: NavController) {
    Button(onClick = { nav.navigate("home") }) {
        Text("Voltar ao Início")
    }
}