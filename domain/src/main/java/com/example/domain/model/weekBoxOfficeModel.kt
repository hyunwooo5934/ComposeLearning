package com.example.domain.model

data class weekBoxOfficeModel(
    val boxofficeType : String, // 박스오피스 종류
    val showRange : String, // 상영기간
    val yearWeekTime : String, // 연도, 주차
    val rnum : String, // 순번
    val rank : String, // 박스오피스 순위
    val rankInten : String, // 순위 증감분
    val rankOldAndNew : String, // 신규진입여부(old, new)
    val movieCd : String, // 영화 대표코드
    val movieNm : String, // 영화명(국문)
    val openDt : String, // 개봉일
    val salesAmt : String, // 해당일 매출액
    val salesShare : String, // 해당일자 상영작의 매출총액 대비 해당 영화의 매출비율
    val salesInten : String, // 전일대비 매출액 증감분
    val salesChange : String, // 전일 대비 매출액 증감 비율
    val salesAcc : String, // 누적매출액
    val audiCnt : String, // 해당일 관객수
    val audiInten : String, // 전일대비 관객수 증감분
    val audiChange : String, // 전일대비 관객수 증감 비율
    val audiAcc : String, // 누적관객수
    val scrnCnt : String, // 해당일자에 상영한 스크린수
    val showCnt : String  // 해당일자에 상영된 횟수
    )
