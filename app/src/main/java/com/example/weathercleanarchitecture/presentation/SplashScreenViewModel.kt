package com.example.weathercleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {
    private val _showSplashScreen = MutableStateFlow(true)
    val showSplashScreen : StateFlow<Boolean> = _showSplashScreen.asStateFlow()

    init {
        viewModelScope.launch {
            delay(30)
            _showSplashScreen.value = false
        }
    }
}