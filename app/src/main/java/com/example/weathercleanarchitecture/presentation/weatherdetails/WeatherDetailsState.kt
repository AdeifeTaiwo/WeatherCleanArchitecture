package com.example.weathercleanarchitecture.presentation.weatherdetails

import com.example.weathercleanarchitecture.data.remote.respond.WeatherResponse

data class WeatherDetailsState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorText: String = "",
    val isCurrentPopularScreen : Boolean = true,
    val weatherData: WeatherResponse? = null
)
