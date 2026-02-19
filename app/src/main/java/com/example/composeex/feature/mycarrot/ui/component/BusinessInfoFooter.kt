package com.example.composeex.presentation.screen.mycarrot.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeex.R

@Composable
fun BusinessInfoFooter(
    expanded: Boolean,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        TextButton(
            onClick = onClick,
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = stringResource(R.string.bus_info_title) ,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.size(15.dp)
                )
            }
        }

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically () + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Text(
                text = stringResource(R.string.bus_info_detail),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray,
                modifier = Modifier.padding(start = 10.dp),
                lineHeight = 12.sp// 필요하면 11~13sp 사이로 조절
            )
        }

        Text(
            text = stringResource(R.string.bus_info_content),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray,
            modifier = Modifier.padding(start = 10.dp),
            lineHeight = 12.sp // 필요하면 11~13sp 사이로 조절
        )
    }
}