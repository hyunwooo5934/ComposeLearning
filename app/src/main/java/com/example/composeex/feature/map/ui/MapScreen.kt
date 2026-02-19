package com.example.composeex.presentation.screen.map

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeex.presentation.screen.home.HomeViewModel
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.core.content.ContextCompat
import com.example.composeex.feature.map.model.SheetState
import com.example.composeex.feature.map.ui.component.MapFieldMenuList
import com.example.composeex.feature.map.ui.component.MapSearchBar
import com.example.composeex.feature.map.ui.component.rememberMapViewWithLifecycle
import com.example.composeex.feature.map.ui.testComponent.BottomSheetContent
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.coroutines.launch


@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val mapView = rememberMapViewWithLifecycle()
    val itemList = listOf("전체", "중고거래", "방금 전", "알바", "동네소식", "부동산", "중고차", "만원당근")

    val context = LocalContext.current
    val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, locationPermission) == PackageManager.PERMISSION_GRANTED
        )
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { hasPermission = it }

    LaunchedEffect(Unit) {
        if (!hasPermission) {
            permissionLauncher.launch(locationPermission)
        }
    }

    // 화면 높이 계산
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }

    // 3단계 높이 정의
    val collapsedHeight = with(density) { 200.dp.toPx() }  // 접힘
    val halfHeight = screenHeightPx * 0.4f                  // 중간 (50%)
    val expandedHeight = screenHeightPx * 0.95f             // 전체 (95%)

    // BottomSheet 상태
    var currentSheetState by remember { mutableStateOf(SheetState.COLLAPSED) }
    var offsetY by remember { mutableFloatStateOf(screenHeightPx - collapsedHeight) }

    val scope = rememberCoroutineScope()

    // 검색창 표시 여부
    val isSearchBarVisible by remember {
        derivedStateOf {
            currentSheetState != SheetState.EXPANDED
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // 지도
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize()
        ) { view ->
            view.getMapAsync { naverMap ->
                naverMap.minZoom = 5.5
                naverMap.uiSettings.isLocationButtonEnabled = false
                naverMap.locationSource = FusedLocationSource(context as Activity, 1000)
                naverMap.locationTrackingMode = if (hasPermission) {
                    LocationTrackingMode.Follow
                } else {
                    LocationTrackingMode.None
                }
            }
        }

        // 검색창 및 메뉴 (전체 펼침 시 숨김)
        AnimatedVisibility(
            visible = isSearchBarVisible,
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                MapSearchBar(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                )

                MapFieldMenuList(
                    itemList = itemList,
                    onClick = { index ->
                        if (itemList.lastIndex == index) {
                            // 화면 이동
                        }
                    }
                )
            }
        }

//         현재 위치 버튼
        FloatingActionButton(
            onClick = { /* 현재 위치 */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .padding(
                    bottom = with(density) {
                        when (currentSheetState) {
                            SheetState.COLLAPSED -> 100.dp
                            SheetState.HALF -> (screenHeightPx - halfHeight).toDp() + 16.dp
                            SheetState.EXPANDED -> 16.dp
                        }
                    }
                ),
            containerColor = Color.White,
            contentColor = Color.Black
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "현재 위치"
            )
        }

        // BottomSheet
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .offset(y = with(density) { offsetY.toDp() })
                .background(
                    Color.White,
                    RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            // 드래그 종료 시 가장 가까운 상태로 스냅
                            val targetState: SheetState
                            val targetOffset: Float

                            when {
                                // 전체 펼침에 가까움
                                offsetY < (screenHeightPx - expandedHeight) +
                                        (screenHeightPx - halfHeight - (screenHeightPx - expandedHeight)) / 2 -> {
                                    targetState = SheetState.EXPANDED
                                    targetOffset = screenHeightPx - expandedHeight
                                }
                                // 중간에 가까움
                                offsetY < (screenHeightPx - halfHeight) +
                                        ((screenHeightPx - collapsedHeight) - (screenHeightPx - halfHeight)) / 2 -> {
                                    targetState = SheetState.HALF
                                    targetOffset = screenHeightPx - halfHeight
                                }
                                // 접힘에 가까움
                                else -> {
                                    targetState = SheetState.COLLAPSED
                                    targetOffset = screenHeightPx - collapsedHeight
                                }
                            }

                            currentSheetState = targetState

                            // 애니메이션으로 이동
                            scope.launch {
                                androidx.compose.animation.core.animate(
                                    initialValue = offsetY,
                                    targetValue = targetOffset,
                                    animationSpec = tween(300)
                                ) { value, _ ->
                                    offsetY = value
                                }
                            }
                        }
                    ) { change, dragAmount ->
                        change.consume()
                        val newOffset = offsetY + dragAmount.y

                        // 범위 제한
                        offsetY = newOffset.coerceIn(
                            screenHeightPx - expandedHeight,
                            screenHeightPx - collapsedHeight
                        )
                    }
                }
        ) {
            BottomSheetContent(
                currentState = currentSheetState,
                onItemClick = { /* 상품 클릭 */ }
            )
        }
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MapScreen(
//    navHostController: NavHostController,
//    viewModel: HomeViewModel = hiltViewModel()
//) {
//    val mapView = rememberMapViewWithLifecycle()
//    val itemList = listOf("전체", "중고거래", "방금 전", "알바", "동네소식", "부동산", "중고차", "만원당근")
//
//    val context = LocalContext.current
//    val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
//    var hasPermission by remember {
//        mutableStateOf(
//            ContextCompat.checkSelfPermission(context, locationPermission) == PackageManager.PERMISSION_GRANTED
//        )
//    }
//    val permissionLauncher = rememberLauncherForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) { hasPermission = it }
//
//    LaunchedEffect(Unit) {
//        if (!hasPermission) {
//            permissionLauncher.launch(locationPermission)
//        }
//    }
//
//    // BottomSheet 상태
//    val sheetState = rememberStandardBottomSheetState(
//        initialValue = SheetValue.PartiallyExpanded,  // 중간부터 시작
//        skipHiddenState = true,
//    )
//    val scaffoldState = rememberBottomSheetScaffoldState(
//        bottomSheetState = sheetState
//    )
//    val scope = rememberCoroutineScope()
//
//    LaunchedEffect(sheetState.targetValue) {
//        if (sheetState.targetValue == SheetValue.Expanded) {
//            scope.launch { sheetState.partialExpand() } // ✅ 손 떼면 중간으로
//        }
//    }
//
//    // 검색창 표시 여부 (전체 펼침 시 숨김)
//    val isSearchBarVisible by remember {
//        derivedStateOf {
//            sheetState.currentValue != SheetValue.Expanded
//        }
//    }
//
//    BottomSheetScaffold(
////        modifier = Modifier.systemBarsPadding(),
//        scaffoldState = scaffoldState,
//        sheetContent = {
//            // BottomSheet 내용
//            BottomSheetContent(
//                onItemClick = { index ->
//                    // 상품 클릭 시 동작
//                }
//            )
//        },
//        sheetPeekHeight = 70.dp,  // 접힘 상태 높이
//        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
//        sheetContainerColor = Color.White,
//        sheetDragHandle = {
//            // 커스텀 드래그 핸들
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 12.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Box(
//                    modifier = Modifier
//                        .width(20.dp)
//                        .height(4.dp)
//                        .background(
//                            Color.Gray.copy(alpha = 0.3f),
//                            RoundedCornerShape(2.dp)
//                        )
//                )
//            }
//        }
//    ) { innerPadding ->
//        Box(modifier = Modifier.fillMaxSize()) {
//            // 지도
//            AndroidView(
//                factory = { mapView },
//                modifier = Modifier.fillMaxSize()
//            ) { view ->
//                view.getMapAsync { naverMap ->
//                    naverMap.minZoom = 5.5
//                    naverMap.uiSettings.isLocationButtonEnabled = false  // 커스텀 버튼 사용
//                    naverMap.locationSource = FusedLocationSource(context as Activity, 1000)
//                    naverMap.locationTrackingMode = if (hasPermission) {
//                        LocationTrackingMode.Follow
//                    } else {
//                        LocationTrackingMode.None
//                    }
//                }
//            }
//
//            // 검색창 및 메뉴 (전체 펼침 시 숨김)
//            AnimatedVisibility(
//                visible = true,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .statusBarsPadding()
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 8.dp)
//                ) {
//                    // 검색창
//                    MapSearchBar(
//                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
//                    )
//
//                    // 필드 메뉴 리스트
//                    MapFieldMenuList(
//                        itemList = itemList,
//                        onClick = { index ->
//                            if (itemList.lastIndex == index) {
//                                // 화면 이동
//                            }
//                        }
//                    )
//                }
//            }
//
//            // 현재 위치 버튼 (우측 하단)
//            FloatingActionButton(
//                onClick = {
//                    // 현재 위치로 이동
//                },
//                modifier = Modifier
//                    .align(Alignment.BottomEnd)
//                    .padding(16.dp)
//                    .padding(
//                        bottom = when (sheetState.currentValue) {
//                            SheetValue.PartiallyExpanded -> 100.dp  // 접힘 상태
//                            SheetValue.Expanded -> 16.dp  // 전체 펼침 (바텀시트에 가려지지 않게)
//                            else -> 100.dp
//                        }
//                    ),
//                containerColor = Color.White,
//                contentColor = Color.Black
//            ) {
//                Icon(
//                    imageVector = Icons.Default.LocationOn,
//                    contentDescription = "현재 위치"
//                )
//            }
//        }
//    }
//}



//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MapScreen(
//    navHostController: NavHostController,
//    viewModel: HomeViewModel = hiltViewModel()
//){
//
//    val mapView = rememberMapViewWithLifecycle()
//
//    val itemList = listOf("전체", "중고거래", "방금 전", "알바", "동네소식", "부동산", "중고차", "만원당근")
//
//    val context = LocalContext.current
//    val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
//    var hasPermission by remember { mutableStateOf(ContextCompat.checkSelfPermission(context, locationPermission) == PackageManager.PERMISSION_GRANTED) }
//    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { hasPermission = it }
//
//    LaunchedEffect(Unit) {
//        if (!hasPermission) { permissionLauncher.launch(locationPermission) }
//    }
//
//    Box(Modifier.fillMaxSize()) {
//
//        AndroidView(
//            factory = { mapView },
//            modifier = Modifier.fillMaxSize()
//        ) { view ->
//            view.getMapAsync { naverMap ->
//                naverMap.minZoom = 5.5
//                naverMap.uiSettings.isLocationButtonEnabled = true
//                naverMap.locationSource = FusedLocationSource(context as Activity, 1000)
//                naverMap.locationTrackingMode = if (hasPermission) LocationTrackingMode.Follow else LocationTrackingMode.None
//
//            }
//        }
//
//        Column (
//            modifier = Modifier
//                .fillMaxSize()
//                .statusBarsPadding() // ✅ 상태바 영역 피해서 내려옴
//                .padding(horizontal = 8.dp)
//        ){
//            // 2) 검색창 (오버레이)
//            MapSearchBar(
//                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
//            )
//
//            MapFieldMenuList(
//                itemList,
//                onClick = {
//                    if(itemList.lastIndex == it) {
//                        // 화면이동
//                    }
//                }
//            )
//
//
//
//
//
//
//        }
//
//
//    }
//
//
//
//}
//
//
