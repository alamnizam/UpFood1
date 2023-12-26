package com.codeturtle.upfood.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Filter(
    val title: String,
    val leadingIcon:ImageVector? = null,
    val trailingIcon:ImageVector? = null,
)