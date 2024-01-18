package com.codeturtle.upfood.ui.theme

import androidx.compose.ui.graphics.Color

sealed class ThemeColor(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val text: Color,
) {
    data object Night : ThemeColor(
        background = Color(0xFF000000),
        surface = Color(0xFF000000),
        primary = Color(0xFF512781),
        secondary = Color(0xFFBA53FE),
        tertiary = Color(0xFFBB83E0),
        text = Color(0xFFFFFFFF)
    )

    data object Day : ThemeColor(
        background = Color(0xFFFFFFFF),
        surface = Color(0xFFFFFFFF),
        primary = Color(0xFF341953),
        secondary = Color(0xFFA248DD),
        tertiary = Color(0xFF8D60AA),
        text = Color(0xFF000000)
    )
}