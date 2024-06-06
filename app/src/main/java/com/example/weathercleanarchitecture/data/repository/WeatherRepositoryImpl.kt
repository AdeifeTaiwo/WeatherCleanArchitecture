package com.example.weathercleanarchitecture.data.repository

import com.example.weathercleanarchitecture.data.remote.WeatherService
import com.example.weathercleanarchitecture.data.remote.respond.WeatherResponse
import com.example.weathercleanarchitecture.domain.repository.WeatherRepository
import com.example.weathercleanarchitecture.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
) : WeatherRepository {

    override suspend fun getCurrentCityWeather(
        latitude: Double,
        longitude: Double
    ): Flow<Resource<WeatherResponse>> {

        return flow {
            emit(Resource.Loading(true))
            val weatherFromRemote = try {
                weatherService.getCurrentCityWeather(latitude, longitude)
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Please Check your internet"))
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error Loading Movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error Loading Movies"))
                return@flow
            }

            emit(Resource.Success(weatherFromRemote))
            emit(Resource.Loading(false))
        }
    }
}