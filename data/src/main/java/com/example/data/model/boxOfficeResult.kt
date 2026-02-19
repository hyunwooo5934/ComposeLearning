package com.example.data.model

import com.google.gson.annotations.SerializedName

data class boxOfficeResult(

    @SerializedName("boxofficeType")
    val boxofficeType : String, // 박스오피스 종류

    @SerializedName("showRange")
    val showRange : String, // 상영기간

    @SerializedName("yearWeekTime")
    val yearWeekTime : String, // 연도, 주차

    @SerializedName("weeklyBoxOfficeList")
    val weeklyBoxOfficeList : List<weeklyBoxOfficeList>, // 박스오피스 영화 정보.
)
