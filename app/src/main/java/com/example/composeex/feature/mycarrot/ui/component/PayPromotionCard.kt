package com.example.composeex.presentation.screen.mycarrot.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composeex.core.ui.theme.Orange

@Composable
fun PayPromotionCard(
    text1: String,
    text2: String,
    dealClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .fillMaxWidth(),
    ) {

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = null,
                tint = Orange,
            )

            Row {
                Text(text1, color = Color.Gray) // 1천만명이 맡고 이용하는
                Text(text2, fontWeight = FontWeight.Bold) // 당근페이
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        Button( // 2번 버튼
            onClick = dealClick,
            modifier = Modifier
                .fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                containerColor = Orange,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("안전한 거래 시작하기", textAlign = TextAlign.Center)
        }
    }

}