package com.example.keyboard_app.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keyboard_app.core.theme.Typography


@Composable
fun Header (text: String)  {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .drawBehind{
                val borderSize = 1.dp.toPx()
                drawLine(
                    color = Color.Gray.copy(alpha = 0.2f),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Row (
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .fillMaxWidth()
        ){
            Text(
                text,
                style = Typography.titleMedium.copy(fontSize = 18.sp)
            )

        }
    }
}