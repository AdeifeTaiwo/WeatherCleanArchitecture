package com.example.weathercleanarchitecture.domain.repository

import com.example.weathercleanarchitecture.data.remote.respond.WeatherResponse
import com.example.weathercleanarchitecture.utility.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCurrentCityWeather(
        latitude: Double,
        longitude: Double
    ): Flow<Resource<WeatherResponse>>

}