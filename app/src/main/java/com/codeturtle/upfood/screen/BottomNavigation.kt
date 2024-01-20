package com.codeturtle.upfood.screen

import android.annotation.SuppressLint
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codeturtle.upfood.R
import com.codeturtle.upfood.naviagtion.AuthScreen
import com.codeturtle.upfood.naviagtion.HomeNavGraph
import com.codeturtle.upfood.naviagtion.sreen_route.BottomBarScreen
import com.codeturtle.upfood.naviagtion.sreen_route.HomeNavScreen
import com.codeturtle.upfood.screen.utils.times
import com.codeturtle.upfood.screen.utils.transform


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomNavigation(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.SavedRecipe,
        BottomBarScreen.Notification,
        BottomBarScreen.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(80.dp), contentAlignment = Alignment.BottomCenter
    ) {
        Scaffold(containerColor = Color.Transparent, bottomBar =
        {
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                content = {
                    NavigationBar(
                        containerColor = Color.Transparent
                    ) {
                        Box {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp),
                                painter = painterResource(R.drawable.bottom_navigation_bg),
                                contentScale = ContentScale.FillBounds,
                                contentDescription = "Bottom navigation background",
                                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.background)
                            )
                            Row {
                                screens.forEach { screen ->
                                    val isSelected = currentRoute == screen.route
                                    NavigationBarItem(
                                        selected = isSelected,
                                        onClick = {
                                            navController.navigate(screen.route.lowercase()) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }, label = {
                                            Text(
                                                text = screen.title, color = if (isSelected) {
                                                    MaterialTheme.colorScheme.primary
                                                } else {
                                                    Color.Gray
                                                }
                                            )
                                        },
                                        alwaysShowLabel = false,
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = Color.White,
                                            selectedTextColor = MaterialTheme.colorScheme.tertiary,
                                            indicatorColor = MaterialTheme.colorScheme.primary,
                                            unselectedIconColor = MaterialTheme.colorScheme.tertiary
                                        ),
                                        icon = {
                                            BadgedBox(badge = {
                                                if (screen.badgeCount != null) {
                                                    Badge {
                                                        Text(text = screen.badgeCount.toString())
                                                    }
                                                }
                                            }) {
                                                Icon(
                                                    imageVector = if (isSelected) {
                                                        screen.selectedIcon
                                                    } else {
                                                        screen.unSelectedIcon
                                                    },
                                                    contentDescription = screen.title,
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            )

        }
        ) {
            HomeNavGraph(navController = navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
private fun getRenderEffect(): RenderEffect {
    val blurEffect = RenderEffect.createBlurEffect(80f, 80f, Shader.TileMode.MIRROR)

    val alphaMatrix = RenderEffect.createColorFilterEffect(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    1f, 0f, 0f, 0f,
                    0f, 0f, 1f, 0f,
                    0f, 0f, 0f, 0f,
                    1f, 0f, 0f, 0f,
                    0f, 0f, 50f, -5000f
                )
            )
        )
    )

    return RenderEffect.createChainEffect(alphaMatrix, blurEffect)
}

@Composable
fun BottomNavigation() {
    val isMenuExtended = remember { mutableStateOf(false) }

    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f, animationSpec = tween(
            durationMillis = 1000, easing = LinearEasing
        ), label = ""
    )

    val renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        getRenderEffect().asComposeRenderEffect()
    } else {
        null
    }

    Navigation(
        renderEffect = renderEffect,
        fabAnimationProgress = fabAnimationProgress
    ) {
        isMenuExtended.value = isMenuExtended.value.not()
    }
}

@Composable
fun Navigation(
    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    toggleAnimation: () -> Unit = { }
) {
    Box(
        Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
    ) {
        val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        when (navBackStackEntry?.destination?.route) {
            BottomBarScreen.Home.route -> {
                bottomBarState.value = true
            }

            BottomBarScreen.SavedRecipe.route -> {
                bottomBarState.value = true
            }

            BottomBarScreen.Notification.route -> {
                bottomBarState.value = true
            }

            BottomBarScreen.Profile.route -> {
                bottomBarState.value = true
            }

            HomeNavScreen.SearchRecipe.route -> {
                bottomBarState.value = false
            }

            HomeNavScreen.RecipeDetail.route -> {
                bottomBarState.value = false
            }

            AuthScreen.Login.route ->{
                bottomBarState.value = false
            }
        }
        CustomBottomNavigation(
            navController = navController,
            bottomBarState = bottomBarState
        )
        AnimatedVisibility(
            visible = bottomBarState.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
            content = {
                FabGroup(renderEffect = renderEffect, animationProgress = fabAnimationProgress)
                FabGroup(
                    renderEffect = null,
                    animationProgress = fabAnimationProgress,
                    toggleAnimation = toggleAnimation
                )
            }
        )

    }
}

@Composable
fun FabGroup(
    animationProgress: Float = 0f,
    renderEffect: androidx.compose.ui.graphics.RenderEffect? = null,
    toggleAnimation: () -> Unit = { }
) {
    Box(
        Modifier
            .fillMaxSize()
            .graphicsLayer { this.renderEffect = renderEffect }
            .padding(bottom = 64.dp), contentAlignment = Alignment.BottomCenter) {

        AnimatedFab(
            icon = Icons.Default.PhotoCamera, modifier = Modifier.padding(
                PaddingValues(
                    bottom = 72.dp, end = 210.dp
                ) * FastOutSlowInEasing.transform(0f, 0.8f, animationProgress)
            ), opacity = LinearEasing.transform(0.2f, 0.7f, animationProgress)
        )

        AnimatedFab(
            icon = Icons.Default.Settings, modifier = Modifier.padding(
                PaddingValues(
                    bottom = 128.dp,
                ) * FastOutSlowInEasing.transform(0.1f, 0.9f, animationProgress)
            ), opacity = LinearEasing.transform(0.3f, 0.8f, animationProgress)
        )

        AnimatedFab(
            icon = Icons.Default.ShoppingCart, modifier = Modifier.padding(
                PaddingValues(
                    bottom = 72.dp, start = 210.dp
                ) * FastOutSlowInEasing.transform(0.2f, 1.0f, animationProgress)
            ), opacity = LinearEasing.transform(0.4f, 0.9f, animationProgress)
        )

        AnimatedFab(
            modifier = Modifier.scale(1f - LinearEasing.transform(0.5f, 0.85f, animationProgress)),
        )

        AnimatedFab(
            icon = Icons.Default.Add,
            modifier = Modifier.rotate(
                225 * FastOutSlowInEasing.transform(0.35f, 0.65f, animationProgress)
            ),
            onClick = toggleAnimation,
//            backgroundColor = Color.Transparent
        )
    }
}

@Composable
fun AnimatedFab(
    modifier: Modifier,
    icon: ImageVector? = null,
    opacity: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
        containerColor = backgroundColor,
        modifier = modifier.scale(1.2f),
        shape = RoundedCornerShape(100)
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = Color.White.copy(alpha = opacity)
            )
        }
    }
}


@Composable
fun BottomNavigationScreen() {
    BottomNavigation()
}

@Preview(
    device = "id:pixel_4a", showBackground = true, backgroundColor = 0xFFD8DDDC
)
@Composable
fun BottomNavigationPreview() {
    BottomNavigationScreen()
}
