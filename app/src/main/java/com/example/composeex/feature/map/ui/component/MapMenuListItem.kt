package com.example.composeex.feature.map.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeex.core.ui.theme.Orange

@Composable
fun MapMenuListItem(
    text: String,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp, // 평소 그림자 크기
            pressedElevation = 12.dp // 눌렸을 때 그림자 크기
        )
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = Orange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Color.Black,
            fontSize = 14.sp,
            fontStyle = FontStyle.Normal
        )
    }


}