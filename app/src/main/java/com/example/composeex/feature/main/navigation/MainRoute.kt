package com.example.composeex.feature.main.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composeex.presentation.MainScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainRoute() {

    val navHostController = rememberNavController()
    MainScreen(navHostController)


}