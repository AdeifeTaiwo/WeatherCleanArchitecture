package com.example.weathercleanarchitecture.data.remote

import com.example.weathercleanarchitecture.data.remote.respond.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("current")
    suspend fun getCurrentCityWeather(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("key") key: String,
        @Query("include") include: String
    ): WeatherResponse


    companion object {
        const val BASE_URL = "https://api.weatherbit.io/v2.0/"
        const val IMAGE_BASE_URL = "http://image.tmdb/t/p/3/w500/"
        const val API_KEY = "17d087657d57406b853f06af2dfbae2e"
        //https://api.weatherbit.io/v2.0/current?lat=35.7796&lon=-78.6382&key=17d087657d57406b853f06af2dfbae2e&include=minutely
    }
}