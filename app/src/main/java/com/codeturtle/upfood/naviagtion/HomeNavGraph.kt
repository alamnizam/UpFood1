package com.codeturtle.upfood.naviagtion

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.codeturtle.upfood.naviagtion.sreen_route.BottomBarScreen
import com.codeturtle.upfood.naviagtion.sreen_route.HomeNavScreen
import com.codeturtle.upfood.screen.topscreens.NotificationScreen
import com.codeturtle.upfood.screen.topscreens.ProfileScreen
import com.codeturtle.upfood.screen.topscreens.SaveRecipesScreen
import com.codeturtle.upfood.screen.topscreens.home.HomeScreen
import com.codeturtle.upfood.screen.topscreens.home.RecipeDetailScreen
import com.codeturtle.upfood.screen.topscreens.home.SearchRecipeScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                onFilterClick = {
                    navController.navigate(Graph.HOME_NAV)
                },
                onSearchClick = {
                    navController.navigate(Graph.HOME_NAV)
                },
                onClick = {
                    navController.navigate(HomeNavScreen.RecipeDetail.route)
                }
            )
        }
        composable(route = BottomBarScreen.SavedRecipe.route) {
            SaveRecipesScreen()
        }
        composable(route = BottomBarScreen.Notification.route) {
            NotificationScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
        searchNavGraph(navController = navController)
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
