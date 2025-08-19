package com.example.keyboard_app.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.keyboard_app.core.theme.Typography

@Composable
fun SettingItem (title: String ,id: Int) {
    Box(
        modifier = Modifier
            .background(Color.Gray.copy(alpha = 0.05f),  shape = RoundedCornerShape(4.dp))
            .height(40.dp)
            .fillMaxWidth()
            .clickable{},
    ){
        Row (
            modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Icon(
                painter = painterResource(id = id),
                contentDescription = "item icon",
                modifier = Modifier.size(30.dp),
                tint = Color.Blue
            )
            Text(title, style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
        }
    }
}