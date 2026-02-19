package com.example.composeex.presentation.screen.mycarrot.ui

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeex.presentation.screen.home.HomeViewModel

@Composable
fun MyCarrotRoute(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()) {

    MyCarrotScreen(
        onSettingsClick = { /* navHostController.navigate(...) */ },
        onProfileClick = { /* ... */ },
        onMenuClick = { sectionIndex, itemIndex -> /* ... */ },
    )

}