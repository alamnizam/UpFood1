package com.codeturtle.upfood.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codeturtle.upfood.R
import com.codeturtle.upfood.naviagtion.Graph
import com.codeturtle.upfood.screen.authentication.AuthViewModel
import com.codeturtle.upfood.screen.utils.CustomButton

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(viewModel = null, navController = rememberNavController())
}

@Composable
fun SplashScreen(
    viewModel: AuthViewModel? = hiltViewModel(),
    navController: NavHostController
) {
    val currentUser = viewModel?.currentUser
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Column {
                TopSection()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MidSection()
                    BottomSection(onClick = {
                        if(currentUser != null){
                            navController.popBackStack()
                            navController.navigate(route = Graph.HOME)
                        }else{
                            navController.popBackStack()
                            navController.navigate(route = Graph.AUTHENTICATION)
                        }
                    })
                }

            }
        }
    }
}

@Composable
fun TopSection() {
    val scale = remember {
        Animatable(0f)
    }
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = {
                    OvershootInterpolator(1f).getInterpolation(it)
                }
            )
        )
    }
    LaunchedEffect(key1 = true){
        isVisible = !isVisible
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(200.dp)
                .height(280.dp)
                .scale(scale.value)
                .padding(top = 60.dp)
        )
    }
    Spacer(modifier = Modifier.padding(10.dp))
    AnimatedVisibility(
        visible = isVisible,
        enter = slideIn(
            initialOffset = { IntOffset(x = it.width, y = 0) },
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing // interpolator
            )
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "100K+ Premium Recipe ",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),

                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@Composable
fun MidSection() {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        isVisible = !isVisible
    }
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 2000,
                easing = LinearEasing // interpolator
            )
        )
    ) {
        Column {
            Text(
                text = "Get\nCooking",
                style = TextStyle(
                    fontSize = 50.sp,
                    lineHeight = 60.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Simple way to find Tasty Recipe",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }

}

@Composable
fun BottomSection(
    onClick: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        isVisible = !isVisible
    }
    Spacer(modifier = Modifier.padding(20.dp))
    AnimatedVisibility(
        visible = isVisible,
        enter = slideIn(
            initialOffset = { IntOffset(x = 0, y = it.height) },
            animationSpec = tween(
                durationMillis = 2000,
                easing = LinearEasing // interpolator
            )
        )
    ) {
        Column {
            CustomButton(buttonText = "Start Cooking", onClick = onClick)
        }
    }

}
