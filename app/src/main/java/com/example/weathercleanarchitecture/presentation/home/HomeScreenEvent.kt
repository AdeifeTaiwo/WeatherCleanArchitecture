package com.example.jetpackcomposeuichallenge.presentation.search

sealed class HomeScreenEvent {

    data class UpdateHomeScreenQuery(val searchQuery: String) : HomeScreenEvent()
    object HomeScreenNews : HomeScreenEvent()
}