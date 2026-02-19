package com.example.data.repository

import com.example.data.api.apiService
import com.example.data.mapper.BoxOfficeListMapper
import com.example.data.util.Util
import com.example.domain.model.weekBoxOfficeModel
import com.example.domain.repository.BoxOfficeSearchRepository
import com.example.domain.util.ApiResult
import com.example.domain.util.safeFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BoxOfficeSearchRepositoryImpl @Inject constructor(private val apiService: apiService
) : BoxOfficeSearchRepository{

     override suspend fun boxOfficeInfoList(
        targetDt: String,
        weekGb: String?,
        itemPerPage: String?,
        multiMovieYn: String?,
        repNationCd: String?,
        wideAreaCd: String?
    ): Flow<ApiResult<List<weekBoxOfficeModel>>> = safeFlow {
        val list = apiService.getBoxOfficeList(Util.CLIENT_REGIST_ID,targetDt,weekGb,itemPerPage,multiMovieYn,repNationCd,wideAreaCd)
        BoxOfficeListMapper.listMapper(list)
    }


}