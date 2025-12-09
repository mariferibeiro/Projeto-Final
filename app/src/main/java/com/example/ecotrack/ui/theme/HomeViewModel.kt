package com.example.ecotrack.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class WeatherState(
    val loading: Boolean = false,
    val temperature: Double? = null,
    val wind: Double? = null,
    val condition: String? = null,
    val error: String? = null
)

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state

    fun fetchWeather(lat: Double = -8.05, lon: Double = -34.9) {
        viewModelScope.launch {

            _state.value = WeatherState(loading = true)

            try {
                val resp = ApiService.api.getCurrentWeather(lat, lon)
                val cw = resp.current_weather

                if (cw == null) {
                    _state.value = WeatherState(error = "Clima indisponível")
                    return@launch
                }

                val cond = when (cw.weathercode) {
                    0 -> "Céu limpo"
                    1,2,3 -> "Parcialmente nublado"
                    in 45..48 -> "Neblina"
                    in 51..67 -> "Chuvisco"
                    in 71..77 -> "Neve"
                    in 80..82 -> "Chuva"
                    in 95..99 -> "Tempestade"
                    else -> "Indefinido"
                }

                _state.value = WeatherState(
                    temperature = cw.temperature,
                    wind = cw.windspeed,
                    condition = cond
                )

            } catch (e: Exception) {
                _state.value = WeatherState(error = "Erro ao buscar clima")
            }
        }
    }
}