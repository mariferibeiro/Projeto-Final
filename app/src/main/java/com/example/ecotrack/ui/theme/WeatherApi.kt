package com.example.ecotrack.ui.theme

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API interface para acesso aos dados climáticos.
 */
interface WeatherApi {

    @GET("v1/forecast")
    suspend fun getCurrentWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") current: Boolean = true
    ): WeatherResponse
}

/**
 * Modelo da resposta principal da API.
 */
data class WeatherResponse(
    val current_weather: CurrentWeather?
)

/**
 * Modelo com dados do clima atual.
 */
data class CurrentWeather(
    val temperature: Double,
    val windspeed: Double,
    val weathercode: Int
)

/**
 * Singleton responsável por fornecer a instância da API via Retrofit (videoaula)
 */
object ApiService {

    val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}
