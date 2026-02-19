package com.example.data.mapper

import android.util.Log
import com.example.data.model.BoxOfficeResponse
import com.example.domain.model.weekBoxOfficeModel

object BoxOfficeListMapper {

    fun listMapper(list : BoxOfficeResponse) : List<weekBoxOfficeModel> {

        Log.e("BoxOfficeListMapper",list.toString())

        return list.boxOfficeResult.weeklyBoxOfficeList?.map {
            weekBoxOfficeModel(
                boxofficeType = list.boxOfficeResult.boxofficeType,
                showRange = list.boxOfficeResult.showRange,
                yearWeekTime = list.boxOfficeResult.yearWeekTime,
                rnum = it.rnum,
                rank = it.rank,
                rankInten = it.rankInten,
                rankOldAndNew = it.rankOldAndNew,
                movieCd = it.movieCd,
                movieNm = it.movieNm,
                openDt = it.openDt,
                salesAmt = it.salesAmt,
                salesShare = it.salesShare,
                salesInten = it.salesInten,
                salesChange = it.salesChange,
                salesAcc = it.salesAcc,
                audiCnt = it.audiCnt,
                audiInten = it.audiInten,
                audiChange = it.audiChange,
                audiAcc = it.audiAcc,
                scrnCnt = it.scrnCnt,
                showCnt = it.showCnt
            )
        } ?:emptyList()

    }


}