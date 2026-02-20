package com.example.composeex.presentation.screen.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.domain.model.weekBoxOfficeModel
import kotlinx.coroutines.launch
import androidx.compose.material3.CircularProgressIndicator
import com.example.book.presentation.screen.home.HomeTopBar
import com.example.composeex.feature.home.model.HomeUiState
import com.example.composeex.feature.home.ui.component.HomeBottomSheet
import com.example.composeex.feature.home.ui.component.HomeDataList
import com.example.composeex.feature.home.ui.component.HomeFieldMenuList
import com.example.composeex.feature.home.ui.component.Scrim


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState
){

    var isMenuExpanded by remember { mutableStateOf(false) }

    var isFabExpanded by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableStateOf(0) }
    val itemList = listOf("전체", "중고거래", "방금 전", "알바", "동네소식", "부동산", "중고차", "만원당근")

    var selectedItem by remember { mutableStateOf<weekBoxOfficeModel?>(null) }
    val scope = rememberCoroutineScope()

    var toggleState by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState (
        skipPartiallyExpanded = true
    )


    Box(modifier = Modifier.fillMaxSize().background(Color.White)){
        Box(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .background(Color.White),
            ) {

                HomeTopBar(
                    expanded = isMenuExpanded,
                    onExpandedChange = { isMenuExpanded = it },
                    click = { it ->
                        when(it) {
                            "onSearchClick" -> {

                            }

                            "onNotiClick" -> {

                            }

                            else -> {

                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))


                HomeFieldMenuList(
                    itemList,
                    selectedIndex,
                    onClick = { selectedIndex = it }
                )

                Spacer(modifier = Modifier.height(10.dp))

                if (uiState.isLoading) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                } else {
                    uiState.data?.let {
                        HomeDataList(
                            it,
                            onClick = {
                                selectedItem = it
                                scope.launch { sheetState.show() }
                            }
                        )
                    }
                }

                selectedItem?.let {
                    HomeBottomSheet(
                        it,
                        sheetState,
                        onDataExchange = { selectedItem = null },
                        onClose = {
                            scope.launch {
                                sheetState.hide()
                                selectedItem = null
                            }
                        }
                    )
                }
            }

            ExtendedFabMenu(
                isExpanded = isFabExpanded,
                onExpandChanged = { isFabExpanded = it }
            )

            if (isMenuExpanded) {
                Scrim(
                    onDismiss = { isMenuExpanded = false },
                    color = Color.Black.copy(alpha = 0.32f)
                )
            }
        }
    }


}






