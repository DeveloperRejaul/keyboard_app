package com.example.keyboard_app.core.components

import android.content.Context
import android.view.View
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
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

    // Animatable states
    val scale = remember { Animatable(1f) }
    val color = remember { Animatable(Color.Red) }

    // Popup animation
    val popupScale = remember { Animatable(0f) }

    Box(
        modifier = modifier
            .height(Size.keyHeight).width(width).graphicsLayer { clip = false }
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        awaitFirstDown()
                        scope.launch { scale.animateTo(0.9f, tween(20)) }
                        scope.launch { color.animateTo(Color.Red.copy(0.5f),tween(20)) }
                        scope.launch { popupScale.animateTo(1f, tween(20)) } // popup appear

                        waitForUpOrCancellation()
                        scope.launch {scale.animateTo(1f,tween(20))}
                        scope.launch {color.animateTo(Color.Red.copy(1f),tween(20))}
                        scope.launch { popupScale.animateTo(0f, tween(20)) } // popup disappear
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                }
                .background(color.value, RoundedCornerShape(8.dp))
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
                                if (isPressed && onLongPress != null) {
                                    longPressFired = true
                                    onLongPress()
                                }

                                if (onPressInterval != null) {
                                    while (isPressed) {
                                        onPressInterval()
                                        keyboardViewModal.vibrate(view)
                                        keyboardViewModal.sound(context)
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
        )
        when {
                content != null -> content()
                label != null -> Text(
                    label,
                    style = TextStyle(fontSize = 22.sp)
                )
        }
        if (isPressed) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = -Size.keyHeight)   // ⬅️ sit inside the reserved lane
                    .size(width + 20.dp, Size.keyHeight + 20.dp)
                    .graphicsLayer {                     // make sure it draws above siblings
                        shadowElevation = 8f
                        clip = false
                    }
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(10.dp))
                    .zIndex(10f),                        // ensure on top
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label.orEmpty(),
                    style = TextStyle(fontSize = 26.sp, color = MaterialTheme.colorScheme.onSurface)
                )
            }
        }
    }
}