package com.example.composeex.feature.community.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composeex.core.ui.component.BaseTopBar


@Composable
fun CommTopBar(
    title: String,
    click: (String) -> Unit
) {

    BaseTopBar (
        left = {
            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal
            )
        },

        right = {
            IconButton(onClick = { click("onSearchClick") }) { Icon(Icons.Default.Search, contentDescription = "Search") }
            IconButton(onClick = { click("onNotiClick") }) { Icon(Icons.Default.Notifications, contentDescription = "Notifications") }
            IconButton(onClick = { click("onMoreClick") }) { Icon(Icons.Default.Menu, contentDescription = "Menu") }
        }
    )

}