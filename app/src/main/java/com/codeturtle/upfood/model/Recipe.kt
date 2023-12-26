package com.codeturtle.upfood.model

data class Recipe(
    val name: String ="",
    val timeToCook: Int = 0,
    val image: Int = 0,
    val rating: Double = 0.0,
    val creator: Creator? = null,
)