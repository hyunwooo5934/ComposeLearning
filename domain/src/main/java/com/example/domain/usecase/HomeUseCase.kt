package com.example.domain.usecase

import com.example.domain.model.weekBoxOfficeModel
import com.example.domain.repository.BoxOfficeSearchRepository
import com.example.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val boxOfficeSearchRepository: BoxOfficeSearchRepository) {

    suspend fun getBoxOfficeList(
        targetDt: String,
        weekGb: String? = null,
        itemPerPage: String?=null,
        multiMovieYn: String?=null,
        repNationCd: String?=null,
        wideAreaCd: String?=null
    ): Flow<ApiResult<List<weekBoxOfficeModel>>> {
        return boxOfficeSearchRepository.boxOfficeInfoList(targetDt,weekGb,itemPerPage,multiMovieYn,repNationCd,wideAreaCd)
    }

}