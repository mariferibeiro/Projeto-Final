package com.example.ecotrack.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    vm: AddViewModel,
    onSave: (String) -> Unit
) {
    val text by vm.text.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = text,
            onValueChange = { vm.updateText(it) },
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Campo para novo hábito" },
            label = { Text("Digite um hábito") }
        )

        Button(
            onClick = { onSave(text) },
            modifier = Modifier
                .padding(top = 16.dp)
                .semantics { contentDescription = "Salvar novo hábito" }
        ) {
            Text("Salvar")
        }
    }
}