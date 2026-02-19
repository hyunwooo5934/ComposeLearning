package com.example.composeex.feature.map.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun MapFieldMenuList(
    itemList: List<String>,
    onClick: (index: Int) -> Unit
){

    LazyRow (
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp), // 시작/끝
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        itemsIndexed(itemList) { index, item ->
            MapMenuListItem(
                text = item,
                onClick = { onClick(index)}
            )
        }
    }

}