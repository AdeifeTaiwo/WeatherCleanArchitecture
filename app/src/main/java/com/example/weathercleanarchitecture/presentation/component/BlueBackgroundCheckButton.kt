package com.example.weathercleanarchitecture.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathercleanarchitecture.ui.theme.blueBtnColor
import com.example.weathercleanarchitecture.ui.theme.transparentGrey


@Composable
fun BlueBackgroundCheckButton(
    modifier: Modifier = Modifier,
    buttonText: String = "Continue",
    enabled: Boolean = false,
    onContinueButtonClick: () -> Unit
) {

    OutlinedButton(
        modifier = modifier
            .height(50.dp)
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        onClick = {
            onContinueButtonClick()
        },
        enabled = enabled,
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) blueBtnColor else transparentGrey,
            disabledContainerColor = transparentGrey
        ),
        border = BorderStroke(1.dp, color = Color.Transparent)
    ) {
        Text(
            text = buttonText,
            style = TextStyle(color = Color.White, fontSize = 16.sp)
        )
    }
}