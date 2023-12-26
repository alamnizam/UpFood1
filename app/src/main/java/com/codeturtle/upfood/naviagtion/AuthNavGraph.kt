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
            LoginScreen(
                onLoginClick = {
                    navController.popBackStack(
                        route = Graph.AUTHENTICATION,
                        inclusive = true
                    )
                    navController.navigate(Graph.HOME)
                },
                onSignUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                },
                onForgotClick = {
                    navController.navigate(AuthScreen.Forgot.route)
                }
            )
        }
        composable(route = AuthScreen.SignUp.route) {
            RegistrationScreen(
                onLoginClick = {
                    navController.navigate(AuthScreen.Login.route)
                },
                onRegisterClick = {
                    navController.popBackStack(
                        route = Graph.AUTHENTICATION,
                        inclusive = true
                    )
                    navController.navigate(Graph.HOME)
                }
            )
        }
        composable(route = AuthScreen.Forgot.route) {
            ForgotPasswordScreen()
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
}