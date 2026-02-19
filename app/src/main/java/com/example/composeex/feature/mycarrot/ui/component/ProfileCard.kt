package com.example.composeex.presentation.screen.mycarrot.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composeex.core.ui.theme.Orange
import com.example.composeex.core.ui.theme.VeryLightOrange
import com.example.composeex.core.ui.theme.veryLightGray

import androidx.compose.runtime.remember
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.LocalIndication

@Composable
fun ProfileCard(
    name: String,
    myTemp: String,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = remember  { MutableInteractionSource() },
                indication = LocalIndication.current,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = veryLightGray,
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(name, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.width(5.dp))

            //
            Text(myTemp,
                color = Orange,
                modifier = Modifier.background(VeryLightOrange)
            )
        }

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(30.dp)
        )

    }
}