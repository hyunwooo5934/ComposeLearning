package com.example.composeex.feature.home.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeex.R

@Composable
fun HomeCarrotListtem(
    text1: String,
    text2: String,
    text3: String,
    text4: String,
    onMoreClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        // 이미지
        Image(
            painter = painterResource(id = R.mipmap.lug),
            contentDescription = null,
            modifier = Modifier
                .weight(3.5f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        // 오른쪽 영역
        Column(
            modifier = Modifier
                .weight(6.5f)
                .fillMaxHeight()
        ) {

            // 상단: 텍스트 + more 버튼
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {

                Column(
                    modifier = Modifier.weight(9f)
                ) {
                    Text(
                        text = text1,
                        maxLines = 2,                 // 🔥 핵심
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = text2,
                        color = Color.Gray,
                        fontSize = 10.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = text3,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                IconButton(
                    onClick = onMoreClick,
                    modifier = Modifier.weight(1f)
                        .align(Alignment.Top)
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More",
                        tint = Color.LightGray

                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // 하단 우측
            if (text4.isNotBlank()) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(0.dp,0.dp,5.dp,0.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "heart",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = text4,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

