package com.codeturtle.upfood.naviagtion.sreen_route

sealed class HomeNavScreen(
    val route: String
){
    data object SearchRecipe: HomeNavScreen(
        route = "SEARCH_RECIPES"
    )
    data object RecipeDetail: HomeNavScreen(
        route = "FILTER_SHEET"
    )
}
