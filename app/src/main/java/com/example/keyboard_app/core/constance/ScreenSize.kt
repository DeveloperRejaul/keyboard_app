package com.example.keyboard_app.core.constance

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

object ScreenSize {
    @Composable
    fun width() = LocalConfiguration.current.screenWidthDp.dp

    @Composable
    fun height() = LocalConfiguration.current.screenHeightDp.dp
}