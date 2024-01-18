package com.codeturtle.upfood.screen.bottom_naviagtion_screens.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.Reviews
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.codeturtle.upfood.R
import com.codeturtle.upfood.model.Creator
import com.codeturtle.upfood.model.Recipe

@Preview(showSystemUi = true)
@Composable
fun ProfilePreview() {
    ProfileScreen()
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        ProfileToolbar(
            onBackArrowClick = {

            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileToolbar(onBackArrowClick: () -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val listItems = getMenuItemsList()
    val contextForToast = LocalContext.current.applicationContext
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFFFFF),
                    titleContentColor = Color(0xFF181818),
                ),
                title = {
                    Text(
                        text = "Profile",

                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF181818),
                            textAlign = TextAlign.Center
                        )
                    )
                },
                actions = {
                    IconButton(onClick = {
                        expanded = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.MoreHoriz,
                            contentDescription = "Open Options"
                        )
                    }
                    DropdownMenu(
                        modifier = Modifier.width(width = 150.dp),
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        },
                        offset = DpOffset(x = (-102).dp, y = (-64).dp),
                        properties = PopupProperties()
                    ) {
                        listItems.forEach { menuItemData ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = menuItemData.text,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        color = Color(0xFF121212)
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = menuItemData.icon,
                                        contentDescription = menuItemData.text,
                                        tint = Color(0xFF121212)
                                    )
                                },
                                onClick = {
                                    Toast.makeText(contextForToast, menuItemData.text, Toast.LENGTH_SHORT)
                                        .show()
                                    expanded = false
                                },
                                enabled = true
                            )
                        }
                    }

                },
                navigationIcon = {
                    IconButton(onClick = onBackArrowClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Navigation"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileTop()
                ProfileInfo()
                Recipes(onRecipeClick = {

                })
            }
        }
    }
}

data class MenuItemData(val text: String, val icon: ImageVector)
fun getMenuItemsList(): ArrayList<MenuItemData> {
    val listItems = ArrayList<MenuItemData>()

    listItems.add(MenuItemData(text = "Share", icon = Icons.Outlined.Share))
    listItems.add(MenuItemData(text = "Rate Recipe", icon = Icons.Outlined.Star))
    listItems.add(MenuItemData(text = "Review", icon = Icons.Outlined.Reviews))
    listItems.add(MenuItemData(text = "Unsaved", icon = Icons.Outlined.Save))

    return listItems
}

@Composable
fun Recipes(
    onRecipeClick: () -> Unit
) {
    val recipes = mutableListOf(
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.r1,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner",
                profile = R.drawable.creator1
            )
        ),
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.r2,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner",
                profile = R.drawable.creator2
            )
        ),
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.r3,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner",
                profile = R.drawable.creator1
            )
        ),
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.r4,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner",
                profile = R.drawable.creator2
            )
        ),
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.r2,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner",
                profile = R.drawable.creator1
            )
        ),
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.r1,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner",
                profile = R.drawable.creator2
            )
        )
    )

    Text(
        text = "Recipes",
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight(600),
            color = Color(0xFF000000),
        )
    )
    LazyColumn {
        items(recipes) { recipe ->
            RecipeList(recipe, onRecipeClick,recipes)
        }
    }
}

@Composable
fun RecipeList(
    recipe: Recipe,
    onRecipeClick: () -> Unit,
    recipes: MutableList<Recipe>
) {
    Card(
        modifier =
        if(recipes.last() == recipe){
            Modifier.fillMaxWidth()
                .padding(
                    start = 10.dp,
                    top = 10.dp,
                    end = 10.dp,
                    bottom = 110.dp
                )
                .clickable(
                    onClick = onRecipeClick
                )
        }else{
            Modifier.fillMaxWidth()
                .padding(10.dp)
                .clickable(
                    onClick = onRecipeClick
                )
        },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier.height(160.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = recipe.image),
                contentDescription = "Recipe ${recipe.name}",
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 160f
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(
                        text = recipe.name,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF)
                        )
                    )
                    Text(
                        text = "By ${recipe.creator?.name}",
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFA9A9A9)
                        )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Timer,
                        contentDescription = "Time to Cook",
                        tint = Color(0xFFA9A9A9)
                    )
                    Spacer(modifier = Modifier.padding(start = 4.dp))
                    Text(
                        modifier = Modifier.wrapContentHeight(),
                        text = "${recipe.timeToCook} mins", style = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFD9D9D9)
                        )
                    )
                    Spacer(modifier = Modifier.padding(start = 10.dp))
                    Box(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(size = 12.dp)
                            ), contentAlignment = Alignment.Center
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

            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(16.dp)
                        .background(
                            color = Color(0xFFFFE1B3),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .padding(start = 7.dp, top = 2.dp, end = 7.dp, bottom = 2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star",
                        tint = Color(color = 0xFFFFAD30)
                    )
                    Text(
                        text = "${recipe.rating}",
                        style = TextStyle(
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

@Composable
fun ProfileInfo() {
    Column {
        Text(
            text = "Afuwape Abiodun",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(600),
                color = Color(0xFF121212),
            )
        )

        Text(
            text = "Chef",
            style = TextStyle(
                fontSize = 11.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFFA9A9A9),
            )
        )

        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Private Chef\nPassionate about food and life 🥘🍲🍝🍱\nMore...",
            style = TextStyle(
                fontSize = 11.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF797979),
            )
        )
    }
}

@Composable
fun ProfileTop() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .shadow(
                    elevation = 30.dp,
                    shape = RoundedCornerShape(100),
                    spotColor = Color.Gray
                )
                .clip(shape = RoundedCornerShape(100)),
            painter = painterResource(id = R.drawable.profile),
            contentScale = ContentScale.Crop,
            contentDescription = "profile image"
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Recipe",

                // Text Style/Smaller Text/Regular
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFA9A9A9)
                )
            )
            Text(
                text = "4",

                // Text Style/Large Text/Bold
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF121212),
                    textAlign = TextAlign.Center
                )
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Followers",
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFA9A9A9)
                )
            )
            Text(
                text = "2.5M",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF121212),
                    textAlign = TextAlign.Center
                )
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Following",
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFA9A9A9)
                )
            )
            Text(
                text = "259",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF121212),
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
