package com.example.weathercleanarchitecture.presentation.weatherdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercleanarchitecture.domain.repository.WeatherRepository
import com.example.weathercleanarchitecture.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private var _weatherDetailState = MutableStateFlow(WeatherDetailsState())
    val movieListState = _weatherDetailState.asStateFlow()


//    fun onEvent(event: MovieListEvent) {
//        when (event) {
//            is MovieListEvent.Paginate -> {
//                if (event.category == Category.POPULAR) {
//                    getPopularMovieList(true)
//                } else if (event.category == Category.UPCOMING) {
//                    getUpComingMovieList(true)
//                }
//            }
//
//            is MovieListEvent.Navigate -> {
//                _weatherDetailState.update {
//                    it.copy(isCurrentPopularScreen = !movieListState.value.isCurrentPopularScreen)
//                }
//            }
//        }
//    }
    fun getCurrentCityWeather(
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            _weatherDetailState.update {
                it.copy(isLoading = true)
            }
            weatherRepository.getCurrentCityWeather(
                latitude = latitude,
                longitude = longitude
            ).collect { result ->

                when (result) {
                    is Resource.Error -> {
                        _weatherDetailState.update {
                            it.copy(
                                isLoading = false,
                                errorText = result.message ?: "Error while loading weather"
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _weatherDetailState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { weatherData ->
                            _weatherDetailState.update {
                                it.copy(
                                    weatherData = weatherData
                                )
                            }
                        }
                    }

                    else -> {}
                }

            }
        }
    }

}