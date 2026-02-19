package com.example.composeex.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composeex.core.navigation.Screen
import com.example.composeex.core.ui.theme.veryLightGray


@Composable
fun BottomNavBar(navController: NavController){

    val navigationItems = listOf(
        Screen.Home,
        Screen.Community,
        Screen.Map,
        Screen.Chats,
        Screen.MyCarrot
    )

    Column {
        Divider(
            thickness = 1.dp,
            color = veryLightGray
        )

        NavigationBar(
            containerColor = Color.White
        ){
            val curretRounte = currentRoute(navController)

            navigationItems.forEach { screen ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title,
                            modifier = Modifier.size(30.dp)
                        )
                    }, label = { Text(screen.title) },
                    selected = curretRounte == screen.route,
                    colors = NavigationBarItemDefaults.colors(
                        selectedTextColor = Color.Black,        // 선택됨
                        unselectedTextColor = Color.LightGray,      // 선택 안됨
                        indicatorColor = Color.Transparent,
                        selectedIconColor = Color.Black,        // 선택됨
                        unselectedIconColor = Color.LightGray      // 선택 안됨
                    ),
                    onClick = {
                        navController.navigate(screen.route){
                            popUpTo(navController.graph.findStartDestination().id) {
                                //현재 화면에서 popUpTo 대상 화면까지의 모든 컴포저블의 상태가 저장
                                saveState = true
                            }
                            //Intent.FLAG_ACTIVITY_SINGLE_TOP과 비슷한 기능
                            launchSingleTop = true

                            //navigate 대상 화면의 ID와 일치하는 저장된 상태가 있는 경우 해당 상태를 복원
                            restoreState = true
                        }
                    }
                )
            }
        }
    }



}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

