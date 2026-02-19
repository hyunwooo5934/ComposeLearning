package com.example.composeex.presentation.screen.mycarrot.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeex.presentation.screen.mycarrot.model.MenuSection

@Composable
fun MenuSectionCard(
    section: MenuSection,
    onItemClick: (itemIndex: Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(section.titleRes) ,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(Modifier.height(8.dp))

        section.items.forEachIndexed { index, item ->
            RowMenu(
                icon = item.icon,
                label = stringResource(item.labelRes),
                onClick = { onItemClick(index) }
            )
        }
    }
}