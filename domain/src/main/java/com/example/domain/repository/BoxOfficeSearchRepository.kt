package com.example.domain.repository

import com.example.domain.model.weekBoxOfficeModel
import com.example.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface BoxOfficeSearchRepository {

    suspend fun boxOfficeInfoList(
         targetDt:String, weekGb: String?, itemPerPage:String?, multiMovieYn:String?, repNationCd:String?, wideAreaCd:String?
    ) : Flow<ApiResult<List<weekBoxOfficeModel>>>

}