package com.example.composeex.presentation.screen.chats



import android.view.ViewGroup
import androidx.compose.animation.core.spring
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.snapTo
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.composeex.feature.chats.model.SheetLevel
import com.naver.maps.map.MapView


@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun ChatsScreen(navHostController: NavHostController) {
    ThreeLevelBottomSheet(
        peekHeight = 64.dp,
        halfRatio = 0.35f,
        expandedTopGap = 80.dp,
        scrimColor = Color(0x66000000),
        content = {
            NaverMapView(modifier = Modifier.fillMaxSize())
        },
        sheet = {
            Spacer(Modifier.height(12.dp))
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            ){
                repeat(30) { idx ->
                    Text("아이템 #$idx", modifier = Modifier.padding(vertical = 10.dp))
                }
            }
        }
    )
}

@Composable
fun ThreeLevelBottomSheet(
    peekHeight: Dp,
    halfRatio: Float,
    expandedTopGap: Dp,
    scrimColor: Color,
    content: @Composable BoxScope.() -> Unit,
    sheet: @Composable ColumnScope.() -> Unit
) {
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val density = LocalDensity.current
        val fullHeightPx = constraints.maxHeight.toFloat()

        val peekPx = with(density) { peekHeight.toPx() }
        val expandedTopGapPx = with(density) { expandedTopGap.toPx() }

        // offset = 시트 top이 내려간 y(px). 값이 클수록 아래(=더 접힘)
        val expandedOffset = expandedTopGapPx.coerceAtLeast(0f)
        val peekOffset = (fullHeightPx - peekPx).coerceAtLeast(expandedOffset)
        val halfOffset = (fullHeightPx * (1f - halfRatio)).coerceIn(expandedOffset, peekOffset)


        val state = remember(density) {
            AnchoredDraggableState(
                initialValue = SheetLevel.Peek,
                anchors = DraggableAnchors { SheetLevel.Peek at 0f }, // 임시(바로 updateAnchors로 교체)
                positionalThreshold = { distance: Float -> distance * 0.5f },
                velocityThreshold = { 1500f }, // ✅ 인자 없음(중요)
                snapAnimationSpec = spring(),
                decayAnimationSpec = splineBasedDecay(density),
                confirmValueChange = { true }
            )
        }

        // ✅ 앵커 3개(Peek/Half/Expanded) 구성
        val anchors: DraggableAnchors<SheetLevel> = remember(expandedOffset, halfOffset, peekOffset) {
            DraggableAnchors {
                SheetLevel.Expanded at expandedOffset
                SheetLevel.Half at halfOffset
                SheetLevel.Peek at peekOffset
            }
        }

        // ✅ 앵커 업데이트(레이아웃 크기 바뀌면 자동 갱신)
        LaunchedEffect(anchors) {
            state.updateAnchors(anchors)
            state.snapTo(SheetLevel.Peek)
        }

        val offsetPx = state.requireOffset()

        // Peek이 아니면 스크림 + 바깥(지도) 터치 차단
        val showScrim by remember {
            derivedStateOf { state.currentValue != SheetLevel.Peek }
        }

        Box(Modifier.fillMaxSize()) {
            // 1) 지도/배경 컨텐츠
            content()

            // 2) 스크림(바깥 클릭 막기)
            if (showScrim) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(scrimColor)
                )
            }

            // 3) 바텀 시트
            Surface(
                tonalElevation = 2.dp,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .offset { IntOffset(0, offsetPx.toInt()) }
                    .anchoredDraggable(
                        state = state,
                        orientation = Orientation.Vertical
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 24.dp)
                ) {
                    // Drag handle
                    Box(
                        Modifier
                            .size(width = 44.dp, height = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(Color(0x33000000), RoundedCornerShape(999.dp))
                    )
                    Spacer(Modifier.height(12.dp))

                    sheet()
                }
            }
        }
    }
}

@Composable
fun NaverMapView(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    AndroidView(
        modifier = modifier,
        factory = {
            MapView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                // ⚠️ 예제용 최소 라이프사이클 호출
                onCreate(null)
                onStart()
                onResume()
                getMapAsync { naverMap ->
                    naverMap.uiSettings.isZoomControlEnabled = false
                    naverMap.uiSettings.isLocationButtonEnabled = false
                }
            }
        }
    )
}
