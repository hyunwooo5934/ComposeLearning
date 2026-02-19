package com.example.composeex.feature.home.model

import com.example.domain.model.weekBoxOfficeModel

data class HomeUiState(
    val isLoading: Boolean = false,
    val data: List<weekBoxOfficeModel>? = null,
    val error: String? = null
)