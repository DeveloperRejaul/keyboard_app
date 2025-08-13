package com.example.keyboard_app.features.keyboard


import android.view.KeyEvent
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.ViewModel
import com.example.keyboard_app.core.ime.ComposeImeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale

class KeyboardViewModal: ViewModel() {

    private val _isCapital = MutableStateFlow(false)
    val isCapital: StateFlow<Boolean> = _isCapital



    fun toggleKeyCapital() {
        _isCapital.value = !_isCapital.value
    }

    fun type (ime: ComposeImeService?, char: String) {
        ime?.currentInputConnection?.commitText(if(_isCapital.value)char.uppercase(Locale.ROOT) else char, 1)
    }

    fun remove (ime: ComposeImeService?) {
        val event = KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)
        ime?.currentInputConnection?.sendKeyEvent(event)
    }

    fun done (ime: ComposeImeService?) {
        ime?.requestHideSelf(0)
    }

    fun enter (ime: ComposeImeService?) {
        val event = KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER,)
        ime?.currentInputConnection?.sendKeyEvent(event)
    }
}