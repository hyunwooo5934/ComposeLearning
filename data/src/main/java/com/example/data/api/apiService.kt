package com.example.data.api

import com.example.data.model.BoxOfficeResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface apiService {

    @GET("kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json")
    suspend fun getBoxOfficeList(
        @Query("key") key: String,
        @Query("targetDt")targetDt: String,
        @Query("weekGb")weekGb: String?,
        @Query("itemPerPage")itemPerPage: String?,
        @Query("multiMovieYn")multiMovieYn: String?,
        @Query("repNationCd")repNationCd: String?,
        @Query("wideAreaCd")wideAreaCd: String?
    ) : BoxOfficeResponse

}