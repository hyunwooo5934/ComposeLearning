package com.example.composeex.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeex.feature.home.navigation.HomeRoute
import com.example.composeex.presentation.screen.chats.ChatsScreen
import com.example.composeex.presentation.screen.community.CommScreen
import com.example.composeex.presentation.screen.map.MapScreen
import com.example.composeex.presentation.screen.mycarrot.ui.MyCarrotRoute

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues
){

    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(paddingValues)
        ) {
        composable(route = Screen.Home.route) {
            HomeRoute(navHostController = navHostController)
        }

        composable(route = Screen.Community.route) {
            CommScreen(navHostController = navHostController)
        }

        composable(route = Screen.Map.route) {
            MapScreen(navHostController = navHostController)
        }

        composable(route = Screen.Chats.route) {
            ChatsScreen(navHostController = navHostController)
        }

        composable(route = Screen.MyCarrot.route) {
            MyCarrotRoute(navHostController = navHostController)
        }

//        composable(route = Screen.MyCarrot.route) {
//            MyScreen(navHostController = navHostController)
//        }


//        composable(route = Screen.Town.route) {
//            TownScreen(navHostController = navHostController)
//        }
    }

}