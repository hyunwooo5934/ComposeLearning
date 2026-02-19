package com.example.data.model

import com.google.gson.annotations.SerializedName

data class weeklyBoxOfficeList(
    @SerializedName("rnum")
    val rnum : String, // 순번

    @SerializedName("rank")
    val rank : String, // 박스오피스 순위

    @SerializedName("rankInten")
    val rankInten : String, // 순위 증감분

    @SerializedName("rankOldAndNew")
    val rankOldAndNew : String, // 신규진입여부(old, new)

    @SerializedName("movieCd")
    val movieCd : String, // 영화 대표코드

    @SerializedName("movieNm")
    val movieNm : String, // 영화명(국문)

    @SerializedName("openDt")
    val openDt : String, // 개봉일

    @SerializedName("salesAmt")
    val salesAmt : String, // 해당일 매출액

    @SerializedName("salesShare")
    val salesShare : String, // 해당일자 상영작의 매출총액 대비 해당 영화의 매출비율

    @SerializedName("salesInten")
    val salesInten : String, // 전일대비 매출액 증감분

    @SerializedName("salesChange")
    val salesChange : String, // 전일 대비 매출액 증감 비율

    @SerializedName("salesAcc")
    val salesAcc : String, // 누적매출액

    @SerializedName("audiCnt")
    val audiCnt : String, // 해당일 관객수

    @SerializedName("audiInten")
    val audiInten : String, // 전일대비 관객수 증감분

    @SerializedName("audiChange")
    val audiChange : String, // 전일대비 관객수 증감 비율

    @SerializedName("audiAcc")
    val audiAcc : String, // 누적관색수

    @SerializedName("scrnCnt")
    val scrnCnt : String, // 해당일자에 상영한 스크린수

    @SerializedName("showCnt")
    val showCnt : String,  // 해당일자에 상영된 횟수

)
