package com.example.composeex.feature.home.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun HomeFieldMenuList(
    itemList: List<String>,
    selectedIndex: Int,
    onClick: (index: Int) -> Unit
){

    LazyRow (
        contentPadding = PaddingValues(10.dp), // 시작/끝
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        itemsIndexed(itemList) { index, item ->
            HomeMenuListItem(
                text = item,
                selected = index == selectedIndex,
                onClick = { onClick(index)}
            )
        }
    }

}