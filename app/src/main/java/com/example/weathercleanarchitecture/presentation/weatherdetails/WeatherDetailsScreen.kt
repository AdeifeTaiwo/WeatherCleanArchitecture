package com.example.weathercleanarchitecture.presentation.weatherdetails

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weathercleanarchitecture.R

@Composable
fun WeatherDetailsScreen(
    viewModel: WeatherDetailsViewModel = hiltViewModel()
) {

    val weatherDetailState = viewModel.movieListState.collectAsState().value

    Surface {
        Column(

        ) {

            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (backButton, weatherImage, temperature, cityName, celciusIcon) = createRefs()

                //weatherImage
                Image(
                    modifier = Modifier
                        .graphicsLayer {}
                        .padding(top = 5.dp, start = 16.dp, end = 0.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .constrainAs(weatherImage) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        },
                    painter = painterResource(R.drawable.weather_large),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )

                //celciusIcon
                Image(
                    modifier = Modifier
                        .graphicsLayer {}
                        .size(32.dp)
                        .padding(top = 5.dp, start = 4.dp, end = 0.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .constrainAs(celciusIcon) {
                            start.linkTo(temperature.end)
                            top.linkTo(temperature.top)
                            bottom.linkTo(temperature.bottom)
                        },
                    painter = painterResource(R.drawable.celcius),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )

                //backButton
                Icon(
                    modifier = Modifier
                        .graphicsLayer {

                        }
                        .padding(top = 40.dp, start = 16.dp, end = 16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .constrainAs(backButton) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                )
                val city = weatherDetailState?.weatherData?.data?.get(0)?.cityName
                val temperatureText =  weatherDetailState?.weatherData?.data?.get(0)?.appTemp
                val humidity =  weatherDetailState?.weatherData?.data?.get(0)?.windSpd
                //city name
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .graphicsLayer {}
                        .constrainAs(cityName) {
                            start.linkTo(parent.start)
                            top.linkTo(backButton.bottom)
                        }
                        .padding(start = 16.dp, bottom = 16.dp, top = 40.dp),
                    text = "$city",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Start
                    )
                )
                //temperature text
                Text(
                    modifier = Modifier
                        .graphicsLayer {}
                        .constrainAs(temperature) {
                            start.linkTo(parent.start)
                            top.linkTo(cityName.bottom)
                        }
                        .padding(start = 16.dp, bottom = 16.dp, top = 40.dp),
                    text = "$temperatureText",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 100.sp,
                        textAlign = TextAlign.Start
                    )
                )


            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val humidity =  weatherDetailState?.weatherData?.data?.get(0)?.windSpd
                RoundContainerWithText(
                    backgroundColor = Color(0XFF658ED9), drawable = R.drawable.rain, text = "90"
                )
                RoundContainerWithText(
                    backgroundColor = Color(0XFFD86191),
                    drawable = R.drawable.ic_humidity,
                    text = "$humidity km/h"
                )
                RoundContainerWithText(
                    backgroundColor = Color(0XFF5E4FC1), drawable = R.drawable.ic_wind, text = "90"
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .graphicsLayer {}
                    .padding(start = 16.dp, bottom = 8.dp, top = 40.dp),
                text = "Today",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start
                )
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {}
                    .padding(top = 0.dp, start = 8.dp, end = 8.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(R.drawable.ic_graph),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(5) {
                    val index = 10 + it;
                    Text("${index} am", style = TextStyle(color = Color.Gray))
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {


                WindSpeedColumn(
                    backgroundColor = Color.White,
                    drawable = R.drawable.fast_wind,
                    temperatureText = "10",
                    timeText = "5"
                )

                WindSpeedColumn(
                    backgroundColor = Color.White,
                    drawable = R.drawable.moon_fast_wind,
                    temperatureText = "10",
                    timeText = "6"
                )

                WindSpeedColumn(
                    backgroundColor = Color.White,
                    drawable = R.drawable.moon_fast_cloud_wind,
                    temperatureText = "10",
                    timeText = "11"
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                items(3) {
                    val index = it
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .graphicsLayer {}
                                .padding(start = 16.dp, bottom = 8.dp, top = 5.dp),
                            text = daysOfWeek[index].first,
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Start
                            )
                        )

                        Image(
                            modifier = Modifier
                                .size(50.dp),
                            painter = painterResource(id = daysOfWeek[index].second),
                            contentDescription = ""
                        )
                    }
                }
            }


        }
    }
}

val daysOfWeek: List<Pair<String, Int>> = listOf(
    Pair("Monday", R.drawable.rain),
    Pair("Tueday", R.drawable.moon_fast_wind),
    Pair("Wednesday", R.drawable.moon_fast_wind),
    Pair("Thurday", R.drawable.rain),
    Pair("Friday", R.drawable.rain),
    Pair("Saturday", R.drawable.rain)
)


@Composable
fun RoundContainerWithText(
    backgroundColor: Color,
    @DrawableRes drawable: Int,
    text: String
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .height(40.dp)
            .width(100.dp)
            .background(shape = RoundedCornerShape(16.dp), color = backgroundColor.copy(0.2f))
    ) {
        Image(
            modifier = Modifier
                .size(32.dp),
            painter = painterResource(id = drawable),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$text", style = TextStyle(
                color = backgroundColor
            )
        )
    }
}

@Composable
fun WindSpeedColumn(
    backgroundColor: Color,
    @DrawableRes drawable: Int,
    temperatureText: String,
    timeText: String
) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(16.dp)
            .width(60.dp)
            .height(140.dp)
            .background(shape = RoundedCornerShape(16.dp), color = Color.White)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp),
                painter = painterResource(id = drawable),
                contentDescription = ""
            )
            Text(
                text = "$temperatureText*C", style = TextStyle(
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = "$timeText am", style = TextStyle(
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}