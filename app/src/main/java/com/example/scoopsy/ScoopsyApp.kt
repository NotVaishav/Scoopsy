package com.example.scoopsy

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.scoopsy.ui.navigation.ScoopsyNavGraph
import com.example.scoopsy.ui.theme.ScoopsyTheme

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ScoopsyScreen(navController: NavHostController = rememberNavController()) {
    ScoopsyNavGraph(navController = navController, scoopsyViewModel = viewModel())
}


@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showBackground = true)
@Composable
fun ScoopsyPreview() {
    ScoopsyTheme {
        ScoopsyScreen()
    }
}