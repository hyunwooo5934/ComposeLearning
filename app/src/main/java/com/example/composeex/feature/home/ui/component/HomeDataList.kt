package com.example.composeex.feature.home.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.composeex.core.ui.theme.veryLightGray
import com.example.domain.model.weekBoxOfficeModel

@Composable
fun HomeDataList(
    itemList: List<weekBoxOfficeModel>,
    onClick: (data: weekBoxOfficeModel) -> Unit
){

    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(itemList) { index, weekBoxOfficeModel ->
            HomeCarrotListtem(
                text1 = weekBoxOfficeModel.rank + ". " + weekBoxOfficeModel.movieNm,
                text2 = weekBoxOfficeModel.openDt,
                text3 = weekBoxOfficeModel.rankOldAndNew,
                text4 = weekBoxOfficeModel.rankInten,
                onMoreClick = { onClick(weekBoxOfficeModel) }
            )

            if (index != itemList.lastIndex) {
                HorizontalDivider(thickness = 1.dp, color = veryLightGray) // 1dp 두께의 회색 줄
            }

        }
    }

}