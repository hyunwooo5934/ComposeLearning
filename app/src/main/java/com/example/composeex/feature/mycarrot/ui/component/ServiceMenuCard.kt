package com.example.composeex.presentation.screen.mycarrot.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.collections.chunked
import kotlin.collections.forEach

import androidx.compose.foundation.LocalIndication
import androidx.compose.ui.res.stringArrayResource
import com.example.composeex.R

@Composable
fun ServiceMenuCard(
    title: String,
    onHeaderClick: () -> Unit,
    onServiceClick: (menu: String) -> Unit
) {

    val services = stringArrayResource(R.array.service_list).toList()

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().clickable(
                interactionSource = remember { MutableInteractionSource()},
                indication = LocalIndication.current, // Explicitly pass the current indication factory
                onClick = onHeaderClick),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, fontWeight = FontWeight.Bold)
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            services.chunked(4).forEach { columnItems ->
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    columnItems.forEachIndexed { index, string ->
                        RowServiceItem(
                            icon = Icons.Default.AccountCircle,
                            text = string,
                            onClick = { onServiceClick(string) }
                        )
                    }
                }
            }
        }
    }
}