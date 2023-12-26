package com.codeturtle.upfood.screen.topscreens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
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
import com.codeturtle.upfood.model.Creator
import com.codeturtle.upfood.model.Filter
import com.codeturtle.upfood.model.Recipe


@Preview(showSystemUi = true)
@Composable
fun SearchRecipeScreen(
    onBackArrowClick: () -> Unit = {},
    onRecipeClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchToolbar(
            onBackArrowClick = onBackArrowClick,
            onRecipeClick = onRecipeClick,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchToolbar(
    onBackArrowClick: () -> Unit = {},
    onRecipeClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFFFFF),
                    titleContentColor = Color(0xFF181818),
                ),
                title = {
                    Text(
                        text = "Search recipes",

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
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                SearchSection()
                CategoryList()
                SearchListSection(onRecipeClick)
            }
        }

    }
}

@Composable
fun SearchListSection(
    onRecipeClick: () -> Unit
) {
    val recipes = mutableListOf(
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image1,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner",
                profile = R.drawable.creator1
            )
        ),
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image2,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner",
                profile = R.drawable.creator2
            )
        ),
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image1,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner",
                profile = R.drawable.creator1
            )
        ),
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image2,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner",
                profile = R.drawable.creator2
            )
        ),
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image1,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner",
                profile = R.drawable.creator1
            )
        ),
        Recipe(
            name = "Classic Greek Salad",
            image = R.drawable.image2,
            timeToCook = 10,
            rating = 4.5,
            creator = Creator(
                name = "James Milner",
                profile = R.drawable.creator2
            )
        )
    )
    Text(
        text = "Recent Search",
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight(600),
            color = Color(0xFF000000)
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(recipes.size) { index ->
            SearchRecipeItem(recipes, index,onRecipeClick)
        }
    }
}

@Composable
fun SearchRecipeItem(
    recipes: MutableList<Recipe>,
    index: Int,
    onRecipeClick:() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(10.dp)
            .clickable(
                onClick = onRecipeClick
            ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier.height(160.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = recipes[index].image),
                contentDescription = "Recipe ${recipes[index].name}",
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
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(
                        text = recipes[index].name,
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF)
                        )
                    )
                    Text(
                        text = "By ${recipes[index].creator?.name}",
                        style = TextStyle(
                            fontSize = 8.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFA9A9A9),

                            )
                    )
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
                        text = "${recipes[index].rating}",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchSection() {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .width(0.dp)
                .weight(8f)
                .border(2.dp, Color(0xFFD9D9D9), RoundedCornerShape(15.dp)),
            value = text,
            onValueChange = {
                text = it
                active = true
            },
            placeholder = {
                Text(
                    text = "Search",
                    style = TextStyle(
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
                                focusManager.clearFocus()
                            }
                        },
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close Icon",
                        tint = Color(0xFFD9D9D9)
                    )
                }
            }
        )

        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp,
                pressedElevation = 10.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF129575),
            ),
            modifier = Modifier
                .height(55.dp)
                .weight(2f)
                .padding(start = 20.dp)

        ) {
            val sheetState = rememberModalBottomSheetState()
            var showBottomSheet by rememberSaveable { mutableStateOf(false) }
//            val scope = rememberCoroutineScope()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = {
                        showBottomSheet = true
                    }),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    imageVector = Icons.Outlined.Tune,
                    contentDescription = "Filter icon",
                    tint = Color.White
                )
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    // Sheet content
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(25.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Title()
                        Time()
                        Rate()
                        Category()
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Button(
                                modifier = Modifier.width(174.dp),
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(color = 0xFF129575)
                                ),
                                shape = RoundedCornerShape(10.dp),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 10.dp
                                )
                            ) {
                                Text(
                                    text = "Filter",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFFFFFFFF),
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }
                        }

                    }
                }
            }

        }
    }
}

@Composable
private fun CategoryList() {
    val categories =
        mutableListOf(
            "All",
            "Indian",
            "Italian",
            "Asian",
            "Chinese",
            "Fruit",
            "Vegetables",
            "Protein",
            "Cereal",
            "Local Dish"
        )
    var index by remember {
        mutableIntStateOf(0)
    }
    LazyRow {
        items(categories) { category ->
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(100.dp)
                    .height(31.dp)
                    .padding(end = 10.dp)
                    .background(
                        color = if (categories.indexOf(category) == index) {
                            Color(0xFF129575)
                        } else {
                            Color.Transparent
                        },
                        shape = RoundedCornerShape(size = 10.dp)
                    )
            ) {
                Text(
                    modifier = Modifier.clickable {
                        index = categories.indexOf(category)
                    },
                    text = category,
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(600),
                        color = if (categories.indexOf(category) == index) {
                            Color(0xFFFFFFFF)
                        } else {
                            Color(0xFF71B1A1)
                        },
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}

@Composable
fun Category() {
    val timeFilters = mutableListOf(
        Filter(
            title = "All"
        ),
        Filter(
            title = "Cereal"
        ),
        Filter(
            title = "Vegetables"
        ),
        Filter(
            title = "Dinner",
            trailingIcon = Icons.Filled.Star
        ),
        Filter(
            title = "Chinese"
        ),
        Filter(
            title = "Local Dish"
        ),
        Filter(
            title = "Fruit"
        ),
        Filter(
            title = "BreakFast"
        ),
        Filter(
            title = "Spanish"
        ),
        Filter(
            title = "Chinese"
        ),
        Filter(
            title = "Lunch"
        )
    )
    Text(
        text = "Category",

        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight(600),
            color = Color(0xFF000000),
        )
    )
    ChipRow(timeFilters, emptySet())
}

@Composable
fun Rate() {
    val timeFilters = mutableListOf(
        Filter(
            title = "5",
            trailingIcon = Icons.Filled.Star
        ),
        Filter(
            title = "4",
            trailingIcon = Icons.Filled.Star
        ),
        Filter(
            title = "3",
            trailingIcon = Icons.Filled.Star
        ),
        Filter(
            title = "2",
            trailingIcon = Icons.Filled.Star
        ),
        Filter(
            title = "1",
            trailingIcon = Icons.Filled.Star
        )
    )
    Text(
        text = "Rate",

        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight(600),
            color = Color(0xFF000000),
        )
    )
    ChipRow(timeFilters, emptySet())
}

@Composable
fun Time() {
    val timeFilters = mutableListOf(
        Filter(
            title = "All"
        ),
        Filter(
            title = "Newest"
        ),
        Filter(
            title = "Oldest"
        ),
        Filter(
            title = "Popularity"
        )
    )
    Text(
        text = "Time",

        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight(600),
            color = Color(0xFF000000),
        )
    )
    ChipRow(timeFilters, emptySet())
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
private fun ChipRow(
    filterLists: MutableList<Filter>,
    tempList: Set<Int>
) {
    var multipleCheck by rememberSaveable {
        mutableStateOf(tempList)
    }
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        maxItemsInEachRow = 5
    ) {
        filterLists.forEachIndexed { index, filter ->
            FilterChip(
                modifier = Modifier.padding(end = 5.dp),
                onClick = {
                    multipleCheck = if (multipleCheck.contains(index)) {
                        multipleCheck.minus(index)
                    } else {
                        multipleCheck.plus(index)
                    }
                },
                label = {
                    Text(
                        text = filter.title,
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = if (multipleCheck.contains(index)) {
                                Color(color = 0xFFFFFFFF)
                            } else {
                                Color(color = 0xFF129575)
                            }

                        )
                    )
                },
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = Color(color = 0xFF129575),
                    borderWidth = 1.dp
                ),
                selected = multipleCheck.contains(index),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.White,
                    selectedContainerColor = Color(color = 0xFF129575)
                ),
                shape = RoundedCornerShape(10.dp),
                elevation = FilterChipDefaults.filterChipElevation(
                    elevation = 5.dp
                ),
                leadingIcon = {
                    filter.leadingIcon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = filter.title,
                            tint = if (multipleCheck.contains(index)) {
                                Color.White
                            } else {
                                Color(color = 0xFF129575)
                            }
                        )
                    }
                },
                trailingIcon = {
                    filter.trailingIcon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = filter.title,
                            tint = if (multipleCheck.contains(index)) {
                                Color.White
                            } else {
                                Color(color = 0xFF129575)
                            }
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun Title() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Filter Search",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000)
            )
        )
    }
}
