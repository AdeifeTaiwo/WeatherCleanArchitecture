package com.example.weathercleanarchitecture.di

import com.example.weathercleanarchitecture.data.repository.WeatherRepositoryImpl
import com.example.weathercleanarchitecture.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieListRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ) : WeatherRepository
}