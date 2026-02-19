package com.example.composeex.presentation.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import com.example.composeex.core.ui.theme.Orange
import androidx.compose.foundation.LocalIndication
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.material3.ExtendedFloatingActionButton

data class MenuItem(
    val icon: ImageVector,
    val label: String,
    val onClick: () -> Unit
)

@Composable
fun ExtendedFabMenu(
    isExpanded: Boolean,
    onExpandChanged: (Boolean) -> Unit
) {


    // 상단 5개 메뉴 아이템
    val topMenuItems = listOf(
        MenuItem(Icons.Default.Edit, "텍스트 글쓰기") { /* TODO */ },
        MenuItem(Icons.Default.Edit, "이미지 업로드") { /* TODO */ },
        MenuItem(Icons.Default.Edit, "동영상 업로드") { /* TODO */ },
        MenuItem(Icons.Default.Edit, "링크 공유") { /* TODO */ },
        MenuItem(Icons.Default.Edit, "투표 만들기") { /* TODO */ }
    )

    // 하단 2개 메뉴 아이템
    val bottomMenuItems = listOf(
        MenuItem(Icons.Default.Edit, "일정 추가") { /* TODO */ },
        MenuItem(Icons.Default.LocationOn, "위치 공유") { /* TODO */ }
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 투명 회색 배경 (메뉴가 열렸을 때만 표시)
        AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(
                        indication = LocalIndication.current,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onExpandChanged(false) }
            )
        }

        // FAB와 메뉴들
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 하단 2개 메뉴 (분리된 그룹)
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                Surface(
                    modifier = Modifier.width(220.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        bottomMenuItems.forEachIndexed { index, item ->
                            MenuItemRow(item = item)
                            if (index < bottomMenuItems.lastIndex) {
                                Divider(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    color = Color.LightGray.copy(alpha = 0.3f),
                                    thickness = 0.5.dp
                                )
                            }
                        }
                    }
                }
            }

            // 상단 5개 메뉴
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                Surface(
                    modifier = Modifier.width(220.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
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
                }
            }

            // ExtendedFloatingActionButton
            val rotation by animateFloatAsState(
                targetValue = if (isExpanded) 45f else 0f,
                label = "rotation"
            )

            if(isExpanded) {
                ExtendedFloatingActionButton(
                    onClick = { onExpandChanged(false) },
                    modifier = Modifier
                        .animateContentSize()
                        .clip(CircleShape),
                    containerColor = Color.White,
                    contentColor =Color.Black
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(rotation)
                    )
                }
            } else {
                ExtendedFloatingActionButton(
                    onClick = { onExpandChanged(true) },
                    modifier = Modifier
                        .animateContentSize()
                        .clip(RoundedCornerShape(30.dp)),
                    containerColor = Orange,
                    contentColor = Color.White,
//                    contentPadding = PaddingValues(
//                        horizontal = 16.dp,
//                        vertical = 6.dp   // 👈 여기 줄이면 바로 낮아짐
//                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = if (isExpanded) "" else "글쓰기",
                        modifier = Modifier
                            .size(20.dp)
                            .rotate(rotation)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "글쓰기", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)

                }
            }

        }
    }
}

@Composable
fun MenuItemRow(item: MenuItem) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable(
                indication = LocalIndication.current,
                interactionSource = interactionSource
            ) { item.onClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.label,
            tint = Color(0xFF333333),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.label,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF333333),
            fontSize = 15.sp
        )
    }
}
