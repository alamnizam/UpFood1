package com.codeturtle.upfood.screen.bottom_naviagtion_screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeturtle.upfood.R
import com.codeturtle.upfood.model.Ingredient
import com.codeturtle.upfood.model.Procedure
import kotlinx.coroutines.launch

enum class TabPage {
    Ingredient,
    Procedure
}

@Preview(showSystemUi = true)
@Composable
fun RecipeDetailScreen(
    onBackArrowClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DetailToolbar(
            onBackArrowClick = onBackArrowClick,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailToolbar(onBackArrowClick: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFFFFF),
                    titleContentColor = Color(0xFF181818),
                ),
                title = {
                    Text(
                        text = "Recipe Detail",

                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF181818),
                            textAlign = TextAlign.Center
                        )
                    )
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
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                RecipeImage()
                RecipeTitle()
                RecipeCreator()
                IngredientAndProcedure()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IngredientAndProcedure() {

    val ingredients = listOf(
        Ingredient(
            id = 1,
            name = "Tomatoes",
            qty = 500,
            sku = "g",
            image = R.drawable.tomatoes
        ),
        Ingredient(
            id = 2,
            name = "Cabbage",
            qty = 300,
            sku = "g",
            image = R.drawable.cabbage
        ),
        Ingredient(
            id = 3,
            name = "Taco",
            qty = 300,
            sku = "g",
            image = R.drawable.taco
        ),
        Ingredient(
            id = 4,
            name = "Slice Bread",
            qty = 300,
            sku = "g",
            image = R.drawable.slice_bread
        ),
        Ingredient(
            id = 5,
            name = "Green Onion",
            qty = 500,
            sku = "g",
            image = R.drawable.green_onion
        ),
        Ingredient(
            id = 6,
            name = "Omelette",
            qty = 500,
            sku = "g",
            image = R.drawable.omelate
        ),
        Ingredient(
            id = 7,
            name = "Hot Dog",
            qty = 300,
            sku = "g",
            image = R.drawable.hot_dog
        ),
        Ingredient(
            id = 8,
            name = "Onion",
            qty = 300,
            sku = "g",
            image = R.drawable.onion
        ),
        Ingredient(
            id = 9,
            name = "Lettuce",
            qty = 100,
            sku = "g",
            image = R.drawable.lettuce
        ),
        Ingredient(
            id = 10,
            name = "Spinach",
            qty = 30,
            sku = "g",
            image = R.drawable.spinach
        ),
        Ingredient(
            id = 11,
            name = "Red & Green Chilli",
            qty = 10,
            sku = "g",
            image = R.drawable.red_green
        ),
        Ingredient(
            id = 12,
            name = "Fries",
            qty = 250,
            sku = "g",
            image = R.drawable.fries
        ),
        Ingredient(
            id = 13,
            name = "Chicken",
            qty = 200,
            sku = "g",
            image = R.drawable.chicken
        ),
        Ingredient(
            id = 13,
            name = "Burger",
            qty = 200,
            sku = "g",
            image = R.drawable.burger
        )
    )

    val procedures = listOf(
        Procedure(
            id = 1,
            step = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        Procedure(
            id = 2,
            step = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?\n" +
                    "Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        Procedure(
            id = 3,
            step = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        Procedure(
            id = 4,
            step = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        Procedure(
            id = 5,
            step = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        Procedure(
            id = 6,
            step = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        Procedure(
            id = 7,
            step = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        Procedure(
            id = 8,
            step = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        Procedure(
            id = 9,
            step = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        Procedure(
            id = 10,
            step = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        )
    )

    val pagerState = rememberPagerState(pageCount = {
        TabPage.entries.size
    })
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabIngredientProcedure(
            selectedTabIndex = pagerState.currentPage,
            onSelectTab = {
                scope.launch {
                    pagerState.animateScrollToPage(it.ordinal)
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dish),
                    contentDescription = "dish icon"
                )
                Spacer(modifier = Modifier.padding(end = 5.dp))
                Text(
                    text = "1 serve",
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFA9A9A9),
                        textAlign = TextAlign.Center
                    )
                )
            }
            Row {
                Text(
                    text = "10 Items",

                    // Text Style/Smaller Text/Regular
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFA9A9A9),
                    )
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            when (index) {
                0 -> Ingredient(ingredients)
                1 -> Procedure(procedures)
            }
        }
    }


}

@Composable
private fun TabIngredientProcedure(
    selectedTabIndex: Int,
    onSelectTab: (TabPage) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(20)),
        containerColor = Color.Transparent,
        indicator = {},
        divider = {},
    ) {
        TabPage.entries.forEachIndexed { index, tabPage ->
            val selected = selectedTabIndex == index
            Tab(
                modifier = if (selected) Modifier
                    .padding(vertical = 10.dp)
                    .clip(RoundedCornerShape(20))
                    .background(
                        Color(0xFF129575)
                    )
                else Modifier
                    .clip(RoundedCornerShape(20))
                    .background(
                        color = Color.Transparent
                    ),
                selected = selected,
                selectedContentColor = Color(0xFF129575),
                unselectedContentColor = Color.Transparent,
                onClick = {
                    onSelectTab(tabPage)
                },
                text = {
                    Text(
                        text = tabPage.name,
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontWeight = FontWeight(600),
                            color = if (selected) {
                                Color(0xFFFFFFFF)
                            } else {
                                Color(0xFF71B1A1)
                            },
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            )
        }
    }
}

@Composable
private fun Procedure(procedures: List<Procedure>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        items(items = procedures) { procedure ->
            ProcedureItem(procedure = procedure)
        }
    }
}

@Composable
private fun Ingredient(ingredients: List<Ingredient>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        items(items = ingredients) { ingredient ->
            IngredientItem(ingredient = ingredient)
        }
    }
}

@Composable
fun ProcedureItem(procedure: Procedure) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color(0xFFD9D9D9), shape = RoundedCornerShape(size = 12.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Step ${procedure.id}",
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF121212),
                    )
                )
                Text(
                    text = procedure.step,
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFA9A9A9)
                    )
                )
            }
        }
    }
}

@Composable
fun IngredientItem(ingredient: Ingredient) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp)
                .background(color = Color(0xFFD9D9D9), shape = RoundedCornerShape(size = 12.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .width(0.dp)
                    .weight(10F)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(space = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(52.dp)
                        .height(52.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = ingredient.image),
                        contentDescription = ingredient.name
                    )
                }
                Text(
                    text = ingredient.name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF121212)
                    )
                )
            }
            Text(
                modifier = Modifier.weight(2F),
                text = "${ingredient.qty} ${ingredient.sku}",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFA9A9A9),
                    textAlign = TextAlign.Center
                )
            )
        }
    }


}

@Composable
fun RecipeCreator() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .width(0.dp)
                .weight(7f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(end = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .clip(shape = RoundedCornerShape(100)),
                    painter = painterResource(id = R.drawable.creator2),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds
                )
            }
            Column {
                Text(
                    text = "Laura wilson",

                    // Text Style/Small Text/Bold
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF121212),
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Location",
                        tint = Color(0xFF71B1A1)
                    )
                    Text(
                        text = "Lagos, Nigeria",
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFA9A9A9)
                        )
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .width(0.dp)
                .height(37.dp)
                .weight(3f),
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF129575)),
            shape = RoundedCornerShape(size = 10.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),
        ) {
            Text(
                text = "Follow",
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center
                )
            )
        }
    }

}

@Composable
fun RecipeTitle() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.width(194.dp),
            text = "Spicy chicken burger with French fries",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000)
            )
        )

        Text(
            modifier = Modifier.width(93.dp),
            text = "(13k Reviews)",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 19.6.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFFA9A9A9),
            )
        )
    }
}

@Composable
fun RecipeImage() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier.height(160.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.d2),
                contentDescription = "Bugger Recipe",
                contentScale = ContentScale.FillBounds
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
                        text = "4.0",
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

            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Timer,
                        contentDescription = "Star",
                        tint = Color(color = 0xFFD9D9D9)
                    )
                    Spacer(modifier = Modifier.padding(start = 5.dp))
                    Text(
                        text = "20 min",
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFD9D9D9),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Spacer(modifier = Modifier.padding(start = 5.dp))
                    Box(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(size = 12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(1.dp)
                                .width(16.dp)
                                .height(16.dp),
                            imageVector = Icons.Outlined.BookmarkAdd,
                            contentDescription = "Save Icon",
                            tint = Color(0xFF129575)
                        )
                    }
                }
            }

        }
    }
}
