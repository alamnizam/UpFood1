package com.codeturtle.upfood.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = ThemeColor.Night.primary,
    onPrimary = ThemeColor.Night.text,
    secondary = ThemeColor.Night.secondary,
    tertiary = ThemeColor.Night.tertiary,
    surface = ThemeColor.Night.surface,
    background = ThemeColor.Night.background

)

private val LightColorScheme = lightColorScheme(
    primary = ThemeColor.Day.primary,
    onPrimary = ThemeColor.Day.text,
    secondary = ThemeColor.Day.secondary,
    tertiary = ThemeColor.Day.tertiary,
    surface = ThemeColor.Day.surface,
    background = ThemeColor.Day.background

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),

    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun UpFoodTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (!darkTheme) {
            LightColorScheme
        } else {
            DarkColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}