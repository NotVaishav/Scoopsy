package com.example.scoopsy.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scoopsy.R
import com.example.scoopsy.data.Items
import com.example.scoopsy.ui.ScoopsyViewModel
import com.example.scoopsy.ui.home.components.AllFlavorColumn
import com.example.scoopsy.ui.home.components.ItemSummary
import com.example.scoopsy.ui.home.components.PopularCard
import com.example.scoopsy.ui.home.components.TopBar
import com.example.scoopsy.ui.home.components.WelcomeCard
import com.example.scoopsy.ui.theme.ScoopsyTheme
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scoopsyViewModel: ScoopsyViewModel = ScoopsyViewModel(),
    navController: NavController
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    val scoopsyUIState by scoopsyViewModel.uiState.collectAsState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                showActionButton = true,
                titleText = "Scoopsy",
                showBadge = scoopsyUIState.cartItems.isNotEmpty()
            )
        },
        modifier = modifier
            .paint(
                painterResource(id = R.drawable.background_main),
                alpha = 0.2f,
                contentScale = ContentScale.Crop
            )
            .fillMaxSize(),
        containerColor = Color.Transparent,
    ) { contentPadding ->
        Column(
            modifier = modifier
                .padding(contentPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            WelcomeCard()
            Row(
                modifier = modifier
                    .padding(vertical = 10.dp)
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Popular Items", modifier = modifier.padding(end = 10.dp))
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                    modifier = modifier
                        .size(16.dp)
                        .padding(bottom = 2.dp),
                    tint = Color(0xFFf1af09)
                )
            }

            LazyRow {
                items(items = Items.filter { it.isPopular }) { item ->
                    PopularCard(popularItem = item, onItemClick = {
                        showBottomSheet = true
                        scoopsyViewModel.setCurrentItem(
                            item
                        )
                    })
                    Spacer(modifier = modifier.size(15.dp))
                }
            }
            Text(text = "All Flavors", modifier = modifier.padding(vertical = 10.dp))
            AllFlavorColumn(
                scoopsyViewModel = scoopsyViewModel,
                onFlavorClick = { showBottomSheet = true },
                context = context
            )
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
                ItemSummary(
                    modifier = modifier,
                    context = context,
                    scoopsyViewModel = scoopsyViewModel,
                    scoopsyUIState = scoopsyUIState,
                    item = scoopsyUIState.currentItem,
                    onCloseClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    ScoopsyTheme {
        HomeScreen(navController = navController)
    }
}