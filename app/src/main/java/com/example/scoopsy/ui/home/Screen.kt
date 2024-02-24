package com.example.scoopsy.ui.home

import android.os.Build
import android.widget.GridLayout
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.scoopsy.R
import com.example.scoopsy.data.Flavor
import com.example.scoopsy.data.Flavors
import com.example.scoopsy.data.Item
import com.example.scoopsy.data.Items
import com.example.scoopsy.ui.theme.ScoopsyTheme
import com.example.scoopsy.ui.theme.md_theme_light_primary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Scoopsy") },
                actions = {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = null
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                modifier = modifier.padding(horizontal = 20.dp),
            )
        },
        modifier = modifier
            .paint(
                painterResource(id = R.drawable.background_main),
                alpha = 0.2f,
                contentScale = ContentScale.Crop
            )
            .fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show bottom sheet") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        },
        containerColor = Color.Transparent,

        ) { contentPadding ->
        Column(
            modifier = modifier
                .padding(contentPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ice_cream_second),
                    contentDescription = null,
                    modifier = modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Column {
                    Spacer(modifier = modifier.size(10.dp))
                    Text(
                        text = "Welcome,",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 30.sp,
                        modifier = modifier.padding(vertical = 10.dp, horizontal = 20.dp)
                    )

                }
                Column(
                    modifier = modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 15.dp, vertical = 10.dp)

                ) {
                    Box(
                        modifier = modifier
                            .background(Color.DarkGray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Dive into a world of delightful flavors",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = modifier
                                .width(150.dp)
                                .padding(),
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
            Text(text = "Popular Items", modifier = modifier.padding(vertical = 8.dp))
            LazyRow() {
                items(items = Items.filter { it.isPopular }) { item ->
                    PopularCard(popularItem = item)
                    Spacer(modifier = modifier.size(15.dp))
                }
            }
            Text(text = "All Flavors", modifier = modifier.padding(vertical = 10.dp))
            AllFlavorColumn()
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                modifier = modifier.height(600.dp),
                dragHandle = {}
            ) {
//                ItemSummary(modifier = modifier, name = )
            }
        }
    }
}

@Composable
fun PopularCard(
    modifier: Modifier = Modifier,
    popularItem: Item
) {
    val context = LocalContext.current
    val totalPrice = String.format(
        Locale.US, "%.2f", (context.getString(popularItem.price)
            .toDouble() + context.getString(popularItem.price).toDouble())
    )
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = modifier
            .size(width = 140.dp, height = 180.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp, pressedElevation = 16.dp),
    ) {
        Box(modifier = modifier) {
            Image(
                painter = painterResource(id = popularItem.image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = modifier.background(
                    Color.DarkGray.copy(alpha = 0.5f),
                    RoundedCornerShape(bottomEnd = 8.dp)
                )
            ) {
                Column(
                    modifier = modifier
                        .align(Alignment.TopStart)
                        .padding(horizontal = 5.dp, vertical = 5.dp)

                ) {
                    Text(
                        text = context.getString(popularItem.name),
                        color = MaterialTheme.colorScheme.surface,
                        style = MaterialTheme.typography.titleSmall,
                    )
                    Text(
                        text = context.getString(popularItem.subType!!),
                        color = MaterialTheme.colorScheme.surface,
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 11.sp,
                    )
                }
            }

            Box(
                modifier = modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(topStart = 8.dp)
                    )
                    .shadow(elevation = 16.dp, shape = RoundedCornerShape(topStart = 8.dp))
                    .clip(
                        RoundedCornerShape(topStart = 8.dp)
                    )
                    .align(Alignment.BottomEnd)
            ) {

                Text(
                    text = "$${totalPrice}",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = modifier
                        .padding(vertical = 2.dp, horizontal = 4.dp),
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}

@Composable
fun AllFlavorColumn(modifier: Modifier = Modifier) {
    Column {
        for (item in Flavors) {
            FlavorCard(flavor = item)
            Spacer(modifier = modifier.size(15.dp))
        }
    }
}


@Composable
fun FlavorCard(modifier: Modifier = Modifier, flavor: Flavor) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .height(120.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Box() {
            Image(
                painter = painterResource(id = flavor.image),
                contentDescription = null,
                modifier = modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = context.getString(flavor.name), modifier = modifier.padding(10.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSummary(
    modifier: Modifier,
    name: String,
    image: Int,
    description: String,
    price: Int,
    scope: CoroutineScope,
    sheetState: SheetState,
    onHideBottomSheet: () -> Unit
) {
    Column {
        Box(modifier = modifier) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null
            )
            Text(text = name)
        }
        Text(text = description)
        Text(text = "Quantity")
    }

    Button(onClick = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                onHideBottomSheet()
            }
        }
    }) {
        Text("Hide bottom sheet")
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ScoopsyTheme {
        HomeScreen()
    }
}