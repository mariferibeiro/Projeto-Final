package com.example.ecotrack.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChallengesViewModel : ViewModel() {

    private val _challenges = MutableStateFlow(
        listOf(
            // Água
            Challenge(1, "Reduzir tempo de banho para 5 minutos"),
            Challenge(2, "Fechar a torneira ao escovar os dentes"),
            Challenge(3, "Reutilizar a água da máquina de lavar"),

            // Energia
            Challenge(4, "Desligar luzes ao sair do cômodo"),
            Challenge(5, "Desconectar aparelhos da tomada"),
            Challenge(6, "Usar luz natural durante o dia"),

            // Lixo / Reciclagem
            Challenge(7, "Separar lixo reciclável"),
            Challenge(8, "Evitar desperdício de alimentos"),
            Challenge(9, "Reutilizar embalagens em casa"),

            // Plástico
            Challenge(10, "Não usar plástico descartável hoje"),
            Challenge(11, "Usar sacola reutilizável ao fazer compras"),
            Challenge(12, "Evitar canudos e copos plásticos"),

            // Mobilidade / Consumo consciente
            Challenge(13, "Caminhar ou pedalar em trajetos curtos"),
            Challenge(14, "Utilizar transporte coletivo hoje"),
            Challenge(15, "Comprar apenas o necessário, evitando excessos")
        )
    )

    val challenges: StateFlow<List<Challenge>> = _challenges

    fun toggle(id: Int) {
        viewModelScope.launch {
            _challenges.value = _challenges.value.map {
                if (it.id == id) it.copy(completed = !it.completed) else it
            }
        }
    }
}
