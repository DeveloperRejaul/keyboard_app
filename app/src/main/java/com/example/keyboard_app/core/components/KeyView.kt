package com.example.keyboard_app.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keyboard_app.core.constance.ScreenSize

@Composable
fun KeyView(
    modifier: Modifier = Modifier,
    label: String ?= null,
    onClick: () -> Unit ?= {},
    content: (@Composable (() -> Unit))?=null,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(3.dp))
            .clickable {
                onClick()
            }
            .height(40.dp).width(ScreenSize.width()/10 - 8.dp),
        contentAlignment = Alignment.Center
    ) {
       if(content != null) {
           content()
       } else if (label != null) Text(label, style = TextStyle(
            fontSize = 18.sp
        ))
    }
}