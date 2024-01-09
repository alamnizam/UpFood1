package com.codeturtle.upfood.screen.bottom_naviagtion_screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codeturtle.upfood.R
import com.codeturtle.upfood.model.Creator
import com.codeturtle.upfood.model.Recipe
import com.codeturtle.upfood.naviagtion.sreen_route.HomeNavScreen
import com.codeturtle.upfood.screen.authentication.AuthViewModel
import com.smarttoolfactory.ratingbar.RatingBar

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
    HomeScreen(viewModel = null,navController = rememberNavController())
}

@Composable
fun HomeScreen(
    viewModel: AuthViewModel? = hiltViewModel(),
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            GreetingSection(
                name = "Hello ${viewModel?.currentUser?.displayName ?: "user"}",
            )
            Spacer(modifier = Modifier.padding(10.dp))
            SearchSection(
                onFilterClick = {
                    navController.navigate(route = HomeNavScreen.SearchRecipe.route)
                },
                onSearchClick = {
                    navController.navigate(route = HomeNavScreen.SearchRecipe.route)
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            SaveList(onClick = {
                navController.navigate(route = HomeNavScreen.RecipeDetail.route)
            })
            Spacer(modifier = Modifier.padding(10.dp))
            NewRecipes(onClick = {
                navController.navigate(route = HomeNavScreen.RecipeDetail.route)
            })
            Spacer(modifier = Modifier.padding(70.dp))
        }
    }
}

@Composable
fun NewRecipes(
    onClick: () -> Unit
) {
    val newRecipes = mutableListOf(
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image1,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner", profile = R.drawable.creator1
            )
        ), Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image2,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner", profile = R.drawable.creator2
            )
        ), Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image1,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner", profile = R.drawable.creator1
            )
        ), Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image2,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner", profile = R.drawable.creator2
            )
        ), Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image1,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner", profile = R.drawable.creator1
            )
        ), Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image2,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner", profile = R.drawable.creator2
            )
        )
    )
    Text(
        text = "New Recipes", style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight(600),
            color = Color(0xFF000000)
        )
    )
    LazyRow {
        items(newRecipes) { recipe ->
            NewRecipesItem(recipe, onClick)
        }
    }
}

@Composable
fun NewRecipesItem(
    recipe: Recipe, onClick: () -> Unit
) {
    var rating by remember { mutableFloatStateOf(3.6f) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .width(251.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .width(261.dp)
                    .wrapContentHeight()
                    .padding(top = 30.dp, end = 10.dp)
                    .background(
                        color = Color.Transparent, shape = RoundedCornerShape(size = 10.dp)
                    ), elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ), shape = RoundedCornerShape(size = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .width(261.dp)
                        .wrapContentHeight()
                        .background(color = Color(0xFFFFFFFF))
                ) {
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 10.dp, end = 10.dp, top = 10.dp, bottom = 0.dp
                            )
                            .width(139.dp),
                        text = "Steak with tomato sauce and bulgur rice.",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF484848)
                        )
                    )
                    RatingBar(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        rating = rating,
                        space = 2.dp,
                        imageVectorEmpty = Icons.Outlined.StarOutline,
                        imageVectorFilled = Icons.Filled.Star,
                        tintEmpty = Color(0xFFFFAD30),
                        tintFilled = Color(0xFFFFAD30),
                        itemSize = 20.dp,
                        itemCount = 5
                    ) {
                        rating = it
                    }
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            recipe.creator?.profile?.let { painterResource(id = it) }?.let {
                                Image(
                                    modifier = Modifier
                                        .width(25.dp)
                                        .height(25.dp)
                                        .shadow(
                                            elevation = 30.dp,
                                            shape = RoundedCornerShape(100),
                                            spotColor = Color.Gray
                                        )
                                        .clip(shape = RoundedCornerShape(100)),
                                    painter = it,
                                    contentDescription = "Creator profile"
                                )
                            }
                            Spacer(modifier = Modifier.padding(start = 10.dp))
                            Text(
                                text = "By ${recipe.creator?.name}", style = TextStyle(
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFA9A9A9),
                                    textAlign = TextAlign.Center
                                )
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.Timer,
                                contentDescription = "Time to Cook",
                                tint = Color(0xFFA9A9A9)
                            )
                            Spacer(modifier = Modifier.padding(start = 10.dp))
                            Text(
                                text = "${recipe.timeToCook} mins", style = TextStyle(
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFA9A9A9),
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp, bottom = 10.dp),
                contentAlignment = TopEnd
            ) {
                Image(
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .clip(shape = RoundedCornerShape(100)),
                    painter = painterResource(id = recipe.image),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Composable
fun SaveList(
    onClick: () -> Unit
) {
    val newRecipes = mutableListOf(
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image1,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner", profile = R.drawable.creator1
            )
        ), Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image2,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner", profile = R.drawable.creator2
            )
        ), Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image1,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner", profile = R.drawable.creator1
            )
        ), Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image2,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner", profile = R.drawable.creator2
            )
        ), Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image1,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner", profile = R.drawable.creator1
            )
        ), Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image2,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner", profile = R.drawable.creator2
            )
        )
    )
    Text(
        text = "Save Recipes", style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight(600),
            color = Color(0xFF000000)
        )
    )
    LazyRow {
        items(newRecipes) { recipe ->
            RecipeItem(recipe, onClick = onClick)
        }
    }
}

@Composable
fun RecipeItem(
    recipe: Recipe, onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .width(150.dp)
            .height(231.dp)
            .padding(end = 15.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .width(150.dp)
                    .wrapContentHeight()
                    .background(
                        color = Color.Transparent, shape = RoundedCornerShape(size = 12.dp)
                    )
                    .padding(top = 60.dp), elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ), shape = RoundedCornerShape(size = 12.dp)
            ) {
                Column(
                    modifier = Modifier.background(color = Color(0xFFD9D9D9))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 60.dp),
                        contentAlignment = Center
                    ) {
                        Text(
                            modifier = Modifier
                                .width(130.dp)
                                .height(42.dp),
                            text = recipe.name,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF484848),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Time",

                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFA9A9A9),
                                )
                            )
                            Text(
                                text = "${recipe.timeToCook} Mins", style = TextStyle(
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF484848)
                                )
                            )

                        }
                        Box(
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                                .background(
                                    color = Color(0xFFFFFFFF),
                                    shape = RoundedCornerShape(size = 12.dp)
                                ), contentAlignment = Center
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(1.dp)
                                    .width(16.dp)
                                    .height(16.dp),
                                imageVector = Icons.Outlined.BookmarkAdd,
                                contentDescription = "Save Icon",
                                tint = Color(0xFFA9A9A9)
                            )
                        }

                    }
                }

            }
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Center
            ) {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clip(shape = RoundedCornerShape(100)),
                    painter = painterResource(id = recipe.image),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds
                )
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(), contentAlignment = TopEnd
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(16.dp)
                            .background(
                                color = Color(0xFFFFE1B3), shape = RoundedCornerShape(size = 20.dp)
                            )
                            .padding(start = 7.dp, top = 2.dp, end = 7.dp, bottom = 2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star",
                            tint = Color(color = 0xFFFFAD30)
                        )
                        Text(
                            text = "${recipe.rating}", style = TextStyle(
                                fontSize = 8.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),

                                textAlign = TextAlign.Right,
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchSection(
    onFilterClick: () -> Unit = {}, onSearchClick: () -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(modifier = Modifier
            .width(0.dp)
            .weight(8f)
            .border(2.dp, Color(0xFFD9D9D9), RoundedCornerShape(15.dp)),
            value = text,
            onValueChange = {
                text = it
                active = true
            },
            singleLine = true,
            enabled = false,
            placeholder = {
                Text(
                    modifier = Modifier.clickable(
                        onClick = onSearchClick
                    ), text = "Search", style = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFD9D9D9)
                    )
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF129575),
                unfocusedBorderColor = Color(0xFFD9D9D9),
                cursorColor = Color(0xFF129575),
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search Icon",
                    tint = Color(0xFFD9D9D9)
                )
            },
            shape = RoundedCornerShape(15.dp),
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                                active = false
                            }
                        },
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close Icon",
                        tint = Color(0xFFD9D9D9)
                    )
                }
            })

        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp, pressedElevation = 10.dp
            ), colors = CardDefaults.cardColors(
                containerColor = Color(0xFF129575),
            ), modifier = Modifier
                .height(55.dp)
                .weight(2f)
                .padding(start = 20.dp)

        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = onFilterClick),
                contentAlignment = Center
            ) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Outlined.Tune,
                    contentDescription = "Filter icon",
                    tint = Color.White
                )
            }

        }
    }
}

@Composable
fun GreetingSection(
    name: String = "Hello user"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.height(60.dp)
        ) {
            Text(
                text = name, style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000)
                )
            )

            Text(
                text = stringResource(R.string.what_are_you_cooking_today), style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFA9A9A9),
                )
            )
        }
        Card(
//            modifier = Modifier.clickable(onClick = {
//                navController.navigate(
//                    route = BottomBarScreen.Profile.route
//                )
//            }),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp, pressedElevation = 10.dp
            ), colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFCE80),
            )
        ) {
            Image(
                modifier = Modifier.size(60.dp),
                painter = painterResource(id = R.drawable.profile_male),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds

            )
        }

    }
}


