package com.example.keyboard_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.keyboard_app.core.theme.Keyboard_appTheme
import com.example.keyboard_app.features.settings.SettingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Keyboard_appTheme {
                SettingScreen()
            }
        }
    }
}
