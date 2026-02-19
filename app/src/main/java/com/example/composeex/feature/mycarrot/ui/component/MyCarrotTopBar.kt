package com.example.composeex.presentation.screen.mycarrot.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun MyCarrotTopBar (
    title: String,
    onSettingsClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            fontStyle = FontStyle.Normal
        )
        IconButton(
            onClick = onSettingsClick
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "settings"
            )
        }
    }
}