package com.thusee.hmi.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thusee.hmi.R
import com.thusee.hmi.ui.navigation.NavigationScreen
import androidx.compose.ui.res.painterResource as painterResource1

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by homeViewModel.uiState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {

        IconButton(modifier = Modifier
            .padding(30.dp)
            .align(Alignment.TopEnd),
            onClick = { navController.navigate(NavigationScreen.Setting.route) }) {
            Icon(
                modifier = Modifier.size(60.dp),
                imageVector = Icons.Filled.Settings,
                contentDescription = "Back",
            )
        }
        Column(
            Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UpDownButton(drawRes = R.drawable.arrowhead_up) {
                homeViewModel.increment()
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .size(height = 120.dp, width = 200.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        shape = RoundedCornerShape(30.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${state.value}",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 64.sp,
                        lineHeight = 93.sp
                    )
                )
            }

            UpDownButton(
                modifier = Modifier.padding(top = 20.dp),
                drawRes = R.drawable.arrowhead_down
            ) {
                homeViewModel.decrement()
            }

        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter),
            painter = painterResource1(id = R.drawable.jaguar_logo),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        )
    }
}

@Composable
fun UpDownButton(
    modifier: Modifier = Modifier,
    drawRes: Int,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.size(height = 100.dp, width = 80.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF27AE60)
        )
    ) {
        Icon(
            painterResource1(id = drawRes), contentDescription = "arrow"
        )
    }
}
