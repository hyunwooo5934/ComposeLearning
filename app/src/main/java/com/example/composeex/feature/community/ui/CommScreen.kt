package com.example.composeex.presentation.screen.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeex.R
import com.example.composeex.core.ui.component.BaseColumn
import com.example.composeex.core.ui.component.BaseTopBar
import com.example.composeex.feature.community.ui.component.CommTopBar
import com.example.composeex.presentation.screen.home.HomeViewModel

@Composable
fun CommScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
){


    BaseColumn (
        content = {
            CommTopBar(
                title = stringResource(R.string.title_my_comm),
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
        }
    )

}