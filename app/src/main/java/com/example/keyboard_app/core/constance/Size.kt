package com.example.keyboard_app.core.constance

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ScreenSize {
    @Composable
    fun width() = LocalConfiguration.current.screenWidthDp.dp

    @Composable
    fun height() = LocalConfiguration.current.screenHeightDp.dp
}


object Size {
   var totalRow = 4
   var keyHeight = 40.dp
   var keyboardVerticalPadding = 4.dp
   var keyboardHorizontalPadding = 6.dp

   var keyboardRowGap = 8.dp

   var keyGap = 6.dp

    var keyboardHeight= (keyHeight + keyboardRowGap + (keyboardVerticalPadding * 2)) * totalRow
   @Composable
   fun keyWidth(): Dp = ScreenSize.width()/10 - keyGap


    @Composable
    fun SpaceKeyWidth(): Dp = (keyWidth()*5) - (keyGap *2)
}

