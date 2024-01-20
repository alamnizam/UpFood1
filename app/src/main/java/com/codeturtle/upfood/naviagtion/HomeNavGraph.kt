package com.codeturtle.upfood.naviagtion

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.codeturtle.upfood.naviagtion.sreen_route.BottomBarScreen
import com.codeturtle.upfood.naviagtion.sreen_route.HomeNavScreen
import com.codeturtle.upfood.screen.bottom_naviagtion_screens.NotificationScreen
import com.codeturtle.upfood.screen.bottom_naviagtion_screens.SaveRecipesScreen
import com.codeturtle.upfood.screen.bottom_naviagtion_screens.home.HomeScreen
import com.codeturtle.upfood.screen.bottom_naviagtion_screens.home.RecipeDetailScreen
import com.codeturtle.upfood.screen.bottom_naviagtion_screens.home.SearchRecipeScreen
import com.codeturtle.upfood.screen.bottom_naviagtion_screens.profile.ProfileScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomBarScreen.SavedRecipe.route) {
            SaveRecipesScreen()
        }
        composable(route = BottomBarScreen.Notification.route) {
            NotificationScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        searchNavGraph(navController = navController)
        authNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.searchNavGraph(navController: NavHostController){
    navigation(
        route = Graph.HOME_NAV,
        startDestination = HomeNavScreen.SearchRecipe.route
    ){
        composable(route = HomeNavScreen.SearchRecipe.route){
            SearchRecipeScreen(
                onBackArrowClick = {
                    navController.popBackStack(
                        route = BottomBarScreen.Home.route,
                        inclusive = false
                    )
                },
                onRecipeClick = {
                    navController.navigate(HomeNavScreen.RecipeDetail.route)
                }
            )
        }
        composable(route = HomeNavScreen.RecipeDetail.route){
            RecipeDetailScreen(
                onBackArrowClick = {
                    navController.popBackStack(
                        route = BottomBarScreen.Home.route,
                        inclusive = false
                    )
                }
            )
        }
    }
}
