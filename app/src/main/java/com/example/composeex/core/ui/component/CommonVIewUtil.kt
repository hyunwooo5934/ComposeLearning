package com.example.composeex.core.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun `verticalSpacer`(heightSize : Int){
    Spacer(modifier = Modifier
        .height(heightSize.dp)
        .fillMaxWidth())
}