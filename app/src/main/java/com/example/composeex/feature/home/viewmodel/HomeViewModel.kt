package com.example.composeex.presentation.screen.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeex.feature.home.model.HomeUiState
import com.example.domain.model.weekBoxOfficeModel
import com.example.domain.usecase.HomeUseCase
import com.example.domain.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

import java.time.LocalDate
import java.time.format.DateTimeFormatter



@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel(){

    val TAG = "HomeViewModel"
//    val _boList = MutableStateFlow<List<weekBoxOfficeModel>>(emptyList())
//    val boList: StateFlow<List<weekBoxOfficeModel>> = _boList

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        getWeekBoxOfficeList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getWeekBoxOfficeList() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)

            val today = LocalDate.now().minusWeeks(1)
            val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            val formattedDate = today.format(formatter)

            Log.e("HomeViewModel", formattedDate)

            homeUseCase.getBoxOfficeList("20260101").collectLatest { result ->
                try{
                    when(result) {
                        is ApiResult.Success -> {
//                            _boList.value = result.value
                            Log.e("HomeViewModel Success: ", result.toString())
                            _uiState.value = HomeUiState(isLoading = false, data = result.value)
                        }

                        is ApiResult.Error -> {
                            Log.e("HomeViewModel Error: ", result.toString())
                            _uiState.value = HomeUiState(isLoading = false, error = "error 발생!")
                        }

                        is ApiResult.Empty -> {
                            Log.e("HomeViewModel Empty: ", result.toString())
                            _uiState.value = HomeUiState(isLoading = false, error = "조회되는 데이터가 없습니다.")
                        }
                    }
                }catch (e : Exception){
                    Log.e(TAG,e.message.toString())
                }


            }
        }
    }

}