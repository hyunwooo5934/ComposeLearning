package com.example.composeex.feature.home.ui.component

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Scrim(onDismiss: () -> Unit, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current, // 클릭 효과 없음
                onClick = onDismiss // 배경 클릭 시 닫기
            )
    )
}