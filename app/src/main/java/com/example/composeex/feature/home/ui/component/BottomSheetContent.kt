package com.example.composeex.feature.home.ui.component

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeex.core.ui.theme.veryLightGray
import com.example.composeex.presentation.screen.home.MenuItem
import com.example.composeex.presentation.screen.home.MenuItemRow
import com.example.domain.model.weekBoxOfficeModel


@Composable
fun BottomSheetContent(
    item: weekBoxOfficeModel,
    onClose: () -> Unit
) {

    val topMenuItems = listOf(
        MenuItem(Icons.Default.Add, "관심 있음") { /* TODO */ },
        MenuItem(Icons.Default.Clear, "관심 없음") { /* TODO */ }
    )

    val secMenuItems = listOf(
        MenuItem(Icons.Default.Edit, "이 글 숨기기") { /* TODO */ },
        MenuItem(Icons.Filled.Edit, "게시글 노출 기준") { /* TODO */ },
        MenuItem(Icons.Default.Edit, "신고하기") { /* TODO */ }
    )


    Box(
        modifier = Modifier.fillMaxWidth()
            .background(Color.White)
    ) {
        Column {
            Column (
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(veryLightGray)
            ){
                topMenuItems.forEachIndexed { index, item ->
                    MenuItemRow(item = item)
                    if (index < topMenuItems.lastIndex) {
                        Divider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Color.LightGray.copy(alpha = 0.3f),
                            thickness = 0.5.dp
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(veryLightGray)
            ) {
                secMenuItems.forEachIndexed { index, item ->
                    MenuItemRow(item = item)
                    if (index < topMenuItems.lastIndex) {
                        Divider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Color.LightGray.copy(alpha = 0.3f),
                            thickness = 0.5.dp
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(veryLightGray)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("닫기", textAlign = TextAlign.Center, fontSize = 15.sp,
                    modifier =
                        Modifier.padding(8.dp)
                            .clickable(
                                indication = LocalIndication.current,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = onClose
                            )
                )
            }
        }
    }


}