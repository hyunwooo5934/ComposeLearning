package com.example.composeex.presentation.screen.mycarrot.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composeex.R


data class MenuItem(@StringRes val labelRes: Int, val icon: ImageVector, val onClick: () -> Unit = {})
data class MenuSection(@StringRes val titleRes: Int, val items: List<MenuItem>)

object MenuConfig {

    private val menuData = mapOf(
        // 나의 거래
        R.string.menu_my_trade to listOf(
            Icons.Default.ShoppingCart to R.string.menu_sell_history,
            Icons.Default.FavoriteBorder to R.string.menu_buy_history,
            Icons.Default.Search to R.string.menu_price_search,
            Icons.Default.FavoriteBorder to R.string.menu_trade_book
        ),

        // 나의 관심
        R.string.menu_my_favorite to listOf(
            Icons.Default.FavoriteBorder to R.string.menu_favorite_list,
            Icons.Default.Notifications to R.string.menu_keyword_alarm
        ),

        // 나의 활동
        R.string.menu_my_activity to listOf(
            Icons.Default.FavoriteBorder to R.string.menu_my_town_post
        ),

        // 나의 비즈니스
        R.string.menu_my_business to listOf(
            Icons.Default.FavoriteBorder to R.string.menu_business_profile,
            Icons.Default.FavoriteBorder to R.string.menu_ad,
            Icons.Default.FavoriteBorder to R.string.menu_owner_lounge
        ),

        // 설정
        R.string.menu_setting to listOf(
            Icons.Default.LocationOn to R.string.menu_town_setting,
            Icons.Default.FavoriteBorder to R.string.menu_town_verify,
            Icons.Default.FavoriteBorder to R.string.menu_qr_scan,
            Icons.Default.Settings to R.string.menu_app_setting
        ),

        // 고객지원
        R.string.menu_support to listOf(
            Icons.Default.FavoriteBorder to R.string.menu_notice,
            Icons.Default.FavoriteBorder to R.string.menu_customer_center,
            Icons.Default.Edit to R.string.menu_feedback,
            Icons.Default.Info to R.string.menu_about,
            Icons.Default.FavoriteBorder to R.string.menu_policy
        )
    )

    val sections: List<MenuSection> = menuData.map { (titleRes, items) ->
        MenuSection(
            titleRes = titleRes,
            items = items.map { (icon, labelRes) ->
                MenuItem(icon = icon, labelRes = labelRes)
            }
        )
    }
}
