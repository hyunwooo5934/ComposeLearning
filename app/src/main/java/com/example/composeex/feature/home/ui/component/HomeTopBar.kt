package com.example.book.presentation.screen.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeex.core.ui.component.BaseTopBar


@Composable
fun HomeTopBar(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    click: (String) -> Unit) {

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = _root_ide_package_.androidx.compose.animation.core.FastOutSlowInEasing
        ),
        label = ""
    )

    val items = listOf("독산동", "가산동", "구로동", "신림동", "봉천동")

    var downtownText by remember { mutableStateOf("내 동네") }

    BaseTopBar (
        left = {
            Box {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    onClick = {
                        onExpandedChange(true)
                    }
                ) {
                    Text(
                        text = downtownText,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Normal
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                            .rotate(rotation),
                        tint = Color.Black
                    )
                }

                DropdownMenu(
                    offset = DpOffset(x = 10.dp, y = 0.dp),
                    expanded = expanded,
                    onDismissRequest = {
                        onExpandedChange(false)
                    }
                ) {
                    items.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                // 선택 처리
                                downtownText = item
                                onExpandedChange(false)
                            }
                        )
                    }
                }
            }
        },

        right = {
            IconButton(onClick = { click("onSearchClick") }) { Icon(Icons.Default.Search, contentDescription = "Search") }
            IconButton(onClick = { click("onNotiClick") }) { Icon(Icons.Default.Notifications, contentDescription = "Notifications") }
            IconButton(onClick = { click("onMoreClick") }) { Icon(Icons.Default.Menu, contentDescription = "Menu") }
        }
    )

}

