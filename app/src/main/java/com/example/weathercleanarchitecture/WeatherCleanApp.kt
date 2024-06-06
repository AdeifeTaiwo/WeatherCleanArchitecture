package com.example.weathercleanarchitecture

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weathercleanarchitecture.presentation.home.HomeScreen
import com.example.weathercleanarchitecture.presentation.weatherdetails.WeatherDetailsScreen
import com.example.weathercleanarchitecture.presentation.weatherdetails.WeatherDetailsViewModel
import com.example.weathercleanarchitecture.utility.Route


@Composable
fun WeatherCleanApp(modifier: Modifier = Modifier) {

    Column(modifier = modifier.fillMaxSize()) {
        val navController = rememberNavController()

        WeatherCleanAppNavHost(
            navController = navController,
            modifier = modifier
                .padding(0.dp)
                .weight(1f),
        )

    }
}

@Composable
fun WeatherCleanAppNavHost(
    navController: NavHostController,
    modifier: Modifier,
    context: Context = LocalContext.current
) {
    NavHost(
        navController = navController,
        startDestination = Route.HOME_SCREEN,
        modifier = modifier
    ) {
        composable(route = Route.HOME_SCREEN) {
            HomeScreen(navigateToWeatherDetails = { lat, long ->
                navigateToWeatherDetailsScreen(navController, lat, long)
            })
        }
        composable(route = Route.WEATHER_DETAIL_SCREEN) {
            val lat =
                navController.previousBackStackEntry?.savedStateHandle?.get<Double>("lat")
            val long =
                navController.previousBackStackEntry?.savedStateHandle?.get<Double>("long")

            WeatherDetailsScreen(
                lat,
                long
            )
        }

    }


}

private fun navigateToWeatherDetailsScreen(
    navController: NavHostController,
    lat: Double,
    long: Double
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("lat", lat)
    navController.currentBackStackEntry?.savedStateHandle?.set("long", long)
    navController.navigate(Route.WEATHER_DETAIL_SCREEN)
}
