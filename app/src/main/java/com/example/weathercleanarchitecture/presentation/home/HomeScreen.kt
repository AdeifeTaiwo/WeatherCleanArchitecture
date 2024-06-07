package com.example.weathercleanarchitecture.presentation.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weathercleanarchitecture.R
import com.example.weathercleanarchitecture.presentation.component.AppSearchBar
import com.example.weathercleanarchitecture.presentation.component.BlueBackgroundCheckButton
import com.example.weathercleanarchitecture.utility.Resource
import com.example.weathercleanarchitecture.utility.getReadableLocation
import com.example.weathercleanarchitecture.utility.getUserLocation

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToWeatherDetails: (Double, Double) -> Unit
) {
    val uiState = viewModel.uiState.value
    var getLocation by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf("") }
    var showToast by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current;
    var isLoading by remember { mutableStateOf(false) }

    var long by remember { mutableDoubleStateOf(7.0) }
    var lat by remember { mutableDoubleStateOf(7.0) }


    val latLng = getUserLocation()

    LaunchedEffect(key1 = Unit, block = {
        latLng.collect() { result ->
            when (result) {
                is Resource.Error -> {
                    isLoading = false
                }

                is Resource.Loading -> {
                    isLoading = true
                }

                is Resource.Success -> {
                    address = getReadableLocation(
                        result.data?.latitude ?: 70.0,
                        result.data?.longitude ?: 32.0,
                        context
                    )
                    isLoading = false
                    long = result.data?.longitude ?: 32.0
                    lat = result.data?.latitude ?: 70.0
                }
            }

        }
    })


    if (showToast) {
        Toast.makeText(context, "Fetching location", Toast.LENGTH_SHORT).show()
        showToast = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(34.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = R.drawable.capital_w), contentDescription = "")
                Column {
                    Text(text = "weather", style = TextStyle(color = Color.Black, fontSize = 24.sp))
                    Text(text = "App", style = TextStyle(color = Color.Gray, fontSize = 24.sp))
                }
            }

        }

        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.weather_icon),
            contentDescription = ""
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppSearchBar(
                    modifier = Modifier.weight(0.7f),
                    text = address,
                    hintText = "Fetching location...",
                    onSearch = { },
                    onValueChange = {

                    })

                BlueBackgroundCheckButton(
                    buttonText = "Check",
                    modifier = Modifier.weight(0.3f),
                    enabled = true
                ) {
                    getLocation = true
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.padding(horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BlueBackgroundCheckButton(
                    buttonText = "Check Forecast",
                    modifier = Modifier.weight(0.8f),
                    enabled = address.isNotEmpty(),
                ) {
                    if (address.isNotEmpty()) {
                        navigateToWeatherDetails(lat, long)
                    } else {
                        showToast = true
                    }
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }

}





