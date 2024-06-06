package com.example.weathercleanarchitecture.presentation.home

import androidx.paging.PagingData

import kotlinx.coroutines.flow.Flow

data class HomeScreenState(
    val searchQuery: String = "",

    )