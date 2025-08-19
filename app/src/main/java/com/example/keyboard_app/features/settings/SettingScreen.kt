package com.example.keyboard_app.features.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.keyboard_app.core.components.Header
import com.example.keyboard_app.core.components.SettingItem
import com.example.keyboard_app.core.constance.Size
import com.example.keyboard_app.R


@Composable
fun SettingScreen () {
    Scaffold { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)) {
            Header("Shohoz Bangla Keyboard")

            Column (
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(Size.keyboardRowGap)
            ){
                SettingItem("Theme", id = R.drawable.theme)
                SettingItem("Keyboard size", id = R.drawable.theme)
                SettingItem("Keyboard Sound and vibration",id = R.drawable.theme)
                SettingItem("Imojis, numbers & symbols",id = R.drawable.theme)
                SettingItem("Typing",id = R.drawable.theme)
                SettingItem("About",id = R.drawable.theme)
            }
        }
    }
}