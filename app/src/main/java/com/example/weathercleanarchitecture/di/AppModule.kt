package com.example.weathercleanarchitecture.di


import com.example.weathercleanarchitecture.data.remote.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client: OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(interceptor).build()


    @Provides
    @Singleton
    fun providesMovieApi(): WeatherService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(WeatherService.BASE_URL)
            .client(client)
            .build()
            .create(WeatherService::class.java)
    }

}