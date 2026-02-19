package com.example.composeex.presentation.screen.mycarrot.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.composeex.R
import com.example.composeex.presentation.screen.mycarrot.ui.component.MyCarrotTopBar
import com.example.composeex.presentation.screen.mycarrot.ui.component.ProfileCard
import com.example.composeex.presentation.screen.mycarrot.ui.component.ServiceMenuCard

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.key
import com.example.composeex.presentation.screen.mycarrot.model.MenuConfig
import com.example.composeex.presentation.screen.mycarrot.ui.component.BusinessInfoFooter
import com.example.composeex.presentation.screen.mycarrot.ui.component.MenuSectionCard
import com.example.composeex.presentation.screen.mycarrot.ui.component.PayPromotionCard
import com.example.composeex.presentation.screen.mycarrot.ui.component.QuickActionsRow

@Composable
fun MyCarrotScreen(
    onSettingsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onMenuClick: (sectionIndex: Int, itemIndex: Int) -> Unit
) {

    var expanded by remember  { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 8.dp)         ,
    ) {

        MyCarrotTopBar(
            title = stringResource(R.string.title_my_carrot),
            onSettingsClick = onSettingsClick
        )

        Column (
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){

            ProfileCard(
                name = "혀눕",
                myTemp = "36.5℃",
                onClick = { onProfileClick }
            )

            PayPromotionCard(
                text1 = stringResource(R.string.promotion_text1),
                text2 = stringResource(R.string.promotion_text2),
                dealClick = {}
            )

            ServiceMenuCard(
                title = stringResource(R.string.menu_my_service),
                onHeaderClick = { },
                onServiceClick = { }
            )


            QuickActionsRow()

            MenuConfig.sections.forEachIndexed { sectionIndex, section ->
                MenuSectionCard(
                    section = section,
                    onItemClick = { itemIndex -> onMenuClick(sectionIndex,itemIndex) }
                )
                if (sectionIndex != MenuConfig.sections.lastIndex) Spacer(Modifier.height(8.dp))
            }


            BusinessInfoFooter(
                expanded = expanded,
                onClick = { expanded = !expanded }
            )

            Spacer(modifier = Modifier.height(20.dp))

        }
    }





}







