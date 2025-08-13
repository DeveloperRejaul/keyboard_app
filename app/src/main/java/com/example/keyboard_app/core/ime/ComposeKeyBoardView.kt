package com.example.keyboard_app.core.ime

import android.content.Context
import android.view.KeyEvent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalContext
import com.example.keyboard_app.core.theme.Keyboard_appTheme

class ComposeKeyBoardView(context: Context) : AbstractComposeView(context) {


    @Composable
    override fun Content() {
        Keyboard_appTheme {
            val context = LocalContext.current as? ComposeImeService
            Button(onClick = {
               val ok = context?.currentInputConnection?.commitText("we", 1) != null

                if(!ok){
                    context?.sendDownUpKeyEvents(KeyEvent.KEYCODE_A)
                }

            }) {
                Text("Hello world")
            }

        }
    }
}