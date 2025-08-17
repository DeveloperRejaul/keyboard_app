package com.example.keyboard_app

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keyboard_app.core.constance.ScreenSize
import com.example.keyboard_app.core.constance.Size
import com.example.keyboard_app.core.theme.Keyboard_appTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val context = LocalContext.current
            var value by remember{ mutableStateOf("") }

            val scale = remember { Animatable(1f) }
            val scope = rememberCoroutineScope()


            Keyboard_appTheme {
                Surface(Modifier.fillMaxSize()) {
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        TextField(
                            value = value,
                            onValueChange = { value = it }
                        )
                        Button(onClick = { context.startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)) }){
                            Text("Open Keyboard Settings")
                        }
                        Box(
                            modifier = Modifier
                                .size(100.dp) // total key size
                                .pointerInput(Unit) {
                                    awaitPointerEventScope {
                                        while (true) {
                                            awaitFirstDown()
                                            scope.launch {
                                                scale.animateTo(0.9f, tween(150)) // shrink background
                                            }

                                            waitForUpOrCancellation()
                                            scope.launch {
                                                scale.animateTo(1f, tween(150)) // back to normal
                                            }
                                        }
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            // 🔹 Background that scales
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .graphicsLayer {
                                        scaleX = scale.value
                                        scaleY = scale.value
                                    }
                                    .background(Color.Red, RoundedCornerShape(8.dp))
                            )

                            // 🔹 Foreground content (not scaled)
                            Text(
                                "Press",
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
