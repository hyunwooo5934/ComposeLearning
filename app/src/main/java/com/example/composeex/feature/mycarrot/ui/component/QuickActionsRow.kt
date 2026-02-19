package com.example.composeex.presentation.screen.mycarrot.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composeex.core.ui.theme.veryLightGray

@Composable
fun QuickActionsRow(

){
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconTextButton(
            icon = Icons.Default.FavoriteBorder,
            label = "관심목록",
            modifier = Modifier.weight(1f),
            onClick = { /* 클릭 이벤트 */ }
        )

        Divider(
            modifier = Modifier
                .height(25.dp)
                .width(1.dp),
            color = veryLightGray
        )

        IconTextButton(
            icon = Icons.Default.CheckCircle,
            label = "최근 본 글",
            modifier = Modifier.weight(1f),
            onClick = { /* 클릭 이벤트 */ }
        )

        Divider(
            modifier = Modifier
                .height(30.dp)
                .width(1.dp),
            color = Color.LightGray
        )

        IconTextButton(
            icon = Icons.Default.Star,
            label = "혜택",
            modifier = Modifier.weight(1f),
            onClick = { /* 클릭 이벤트 */ }
        )
    }
}