package com.codeturtle.upfood.naviagtion

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.codeturtle.upfood.screen.authentication.ForgotPasswordScreen
import com.codeturtle.upfood.screen.authentication.LoginScreen
import com.codeturtle.upfood.screen.authentication.RegistrationScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = AuthScreen.SignUp.route) {
            RegistrationScreen(navController = navController)
        }
        composable(route = AuthScreen.Forgot.route) {
            ForgotPasswordScreen(navController = navController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object Login : AuthScreen(route = "LOGIN")
    data object SignUp : AuthScreen(route = "SIGN_UP")
    data object Forgot : AuthScreen(route = "FORGOT")
}