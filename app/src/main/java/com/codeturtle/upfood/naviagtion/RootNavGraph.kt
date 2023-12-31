package com.codeturtle.upfood.naviagtion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codeturtle.upfood.screen.BottomNavigationScreen
import com.codeturtle.upfood.screen.SplashScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.SPLASH) {
            SplashScreen {
                navController.popBackStack()
                navController.navigate(route = Graph.AUTHENTICATION)
            }
        }
        composable(route = Graph.HOME) {
            BottomNavigationScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val SPLASH = "splash_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val HOME_NAV = "home_nav_graph"
}