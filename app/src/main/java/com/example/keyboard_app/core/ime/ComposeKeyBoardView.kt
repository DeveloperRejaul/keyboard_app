package com.example.keyboard_app.core.ime

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import com.example.keyboard_app.core.theme.Keyboard_appTheme
import com.example.keyboard_app.features.keyboard.KeyboardView

class ComposeKeyBoardView(context: Context) : AbstractComposeView(context) {

    @Composable
    override fun Content() {
        Keyboard_appTheme {
            KeyboardView()
        }
    }
}