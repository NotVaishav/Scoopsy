package com.example.scoopsy

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.scoopsy.ui.navigation.ScoopsyNavGraph
import com.example.scoopsy.ui.theme.ScoopsyTheme

@Composable
fun ScoopsyScreen(navController: NavHostController = rememberNavController()) {
    ScoopsyNavGraph(navController = navController)
}


@Preview(showBackground = true)
@Composable
fun ScoopsyPreview() {
    ScoopsyTheme {
        ScoopsyScreen()
    }
}