package com.example.composeex.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.composeex.presentation.common.BottomNavBar
import com.example.composeex.core.navigation.NavGraph
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navHostController: NavHostController) {

    var isFabExpanded by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {

        // ✅ 배경은 시스템바까지 꽉
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
        )

        Scaffold(
            // ✅ 핵심: Scaffold가 systemBars 인셋을 content에 자동 적용하지 못하게 막기
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = { BottomNavBar(navHostController) }
        ) { paddingValues ->

            // ✅ bottomBar 높이만큼만 padding 적용 (이건 유지)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                NavGraph(
                    navHostController = navHostController,
                    paddingValues = PaddingValues(0.dp)
                )
            }
        }

        AnimatedVisibility(
            visible = isFabExpanded,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(
                        indication = null, // ✅ 반드시 null (LocalIndication 금지)
                        interactionSource = remember { MutableInteractionSource() }
                    ) { isFabExpanded = false }
            )
        }
    }
}