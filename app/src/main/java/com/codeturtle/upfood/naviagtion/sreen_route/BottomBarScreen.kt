package com.codeturtle.upfood.naviagtion.sreen_route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NotificationAdd
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.NotificationAdd
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val badgeCount: Int? = null,
){
    data object Home: BottomBarScreen(
        route = "Home",
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    )

    data object SavedRecipe: BottomBarScreen(
        route = "Save Recipe",
        title = "Save Recipe",
        selectedIcon = Icons.Filled.BookmarkAdd,
        unSelectedIcon = Icons.Outlined.BookmarkAdd
    )

    data object Notification: BottomBarScreen(
        route = "Notification",
        title = "Notification",
        selectedIcon = Icons.Filled.NotificationAdd,
        unSelectedIcon = Icons.Outlined.NotificationAdd
    )

    data object Profile: BottomBarScreen(
        route = "Profile",
        title = "Profile",
        selectedIcon = Icons.Filled.Person2,
        unSelectedIcon = Icons.Outlined.Person2
    )
}
