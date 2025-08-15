package com.example.keyboard_app.core.components

import android.content.Context
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.keyboard_app.core.constance.Size
import com.example.keyboard_app.features.keyboard.KeyboardViewModal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun KeyView(
    modifier: Modifier = Modifier,
    label: String ?= null,
    onClick: () -> Unit ?= {},
    width: Dp = Size.keyWidth(),
    keyboardViewModal: KeyboardViewModal = viewModel(),
    context: Context = LocalContext.current,
    view: View = LocalView.current,
    onPressInterval: (() -> Unit) ?= null,
    onLongPress: (() -> Unit) ?= null,
    content: (@Composable (() -> Unit))?=null,
) {
    var isPressed by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(3.dp))
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        awaitFirstDown()
                        isPressed = true

                        keyboardViewModal.vibrate(view)
                        keyboardViewModal.sound(context)
                        var longPressFired = false


                      val job = scope.launch {
                            delay(500)
                            if(isPressed && onLongPress != null) {
                                longPressFired = true
                                onLongPress()
                            }

                            if (onPressInterval != null) {
                                while (isPressed) {
                                    onPressInterval()
                                    delay(100)
                                }
                            }
                        }
                        waitForUpOrCancellation()
                        isPressed = false
                        job.cancel()
                        if (!longPressFired || onLongPress == null) {
                            onClick()
                        }
                    }
                }
            }
            .height(Size.keyHeight)
            .width(width),
        contentAlignment = Alignment.Center
    ) {
       if(content != null) {
           content()
       } else if (label != null) Text(label, style = TextStyle(
            fontSize = 18.sp
        ))
    }
}