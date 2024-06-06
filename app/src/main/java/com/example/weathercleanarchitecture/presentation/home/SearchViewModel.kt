package com.example.weathercleanarchitecture.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.jetpackcomposeuichallenge.presentation.search.HomeScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    //private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _uiState = mutableStateOf(HomeScreenState())
    val uiState : State<HomeScreenState> = _uiState

    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.UpdateHomeScreenQuery -> {
                _uiState.value = uiState.value.copy(
                    searchQuery = event.searchQuery
                )
            }

            is HomeScreenEvent.HomeScreenNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews() {
//        val articles = newsUseCases.searchNews(
//            searchQuery = uiState.value.searchQuery,
//            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
//        ).cachedIn(viewModelScope)
//
//        _uiState.value = uiState.value.copy(articles = articles)
    }
}