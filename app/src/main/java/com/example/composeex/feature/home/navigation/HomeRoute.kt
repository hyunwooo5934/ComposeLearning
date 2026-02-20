package com.example.composeex.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeex.presentation.screen.home.HomeScreen
import com.example.composeex.presentation.screen.home.HomeViewModel


@Composable
fun HomeRoute(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
){

    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(uiState)

}