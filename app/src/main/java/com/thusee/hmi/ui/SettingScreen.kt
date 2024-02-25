package com.thusee.hmi.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thusee.hmi.R

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {

    Box(modifier = modifier.fillMaxSize()) {
        IconButton(
            modifier = Modifier
                .padding(30.dp)
                .align(Alignment.TopStart),
            onClick = { navController?.navigateUp() }) {
            Icon(
                modifier = Modifier.size(60.dp),
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.jaguar_rounded_logo),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}

@Preview
@Composable
fun SettingScreenPreview() {
    SettingScreen()
}