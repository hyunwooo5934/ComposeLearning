package com.example.composeex.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(
    var title: String,
    var route: String,
    var icon: ImageVector

) {

    data object Home :
        Screen(NavigationType.HOME,NavigationType.HOME, Icons.Filled.Home)

    data object Community :
        Screen(NavigationType.COMMUNITY,NavigationType.COMMUNITY, Icons.Filled.Face)

    data object Map :
        Screen(NavigationType.MAP,NavigationType.MAP, Icons.Filled.LocationOn)

    data object Chats :
        Screen(NavigationType.CHATS,NavigationType.CHATS, Icons.Filled.Call)

    data object MyCarrot :
        Screen(NavigationType.MYCARROT,NavigationType.MYCARROT, Icons.Filled.Person)

//    data object Town :
//        Screen(NavigationType.TOWN,NavigationType.TOWN,Icons.Filled.Person)

}

class NavigationType {
    companion object {
        const val HOME = "home"
        const val COMMUNITY = "commu"
        const val MAP = "map"
        const val CHATS = "chats"
        const val MYCARROT = "mycarr"

//        const val TOWN = "town"
    }
}