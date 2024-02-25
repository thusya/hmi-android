package com.thusee.hmi.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thusee.hmi.ui.SettingScreen
import com.thusee.hmi.ui.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        route = Graph.COUNTER_GRAPH,
        startDestination = NavigationScreen.Home.route
    ) {
        composable(NavigationScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(NavigationScreen.Setting.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }) {
            SettingScreen(navController = navController)
        }

    }
}

sealed class NavigationScreen(val route: String) {
    data object Home : NavigationScreen("home")
    data object Setting : NavigationScreen("settings")
}

object Graph {
    const val COUNTER_GRAPH = "counter_graph"
}