package com.example.keyboard_app.features.keyboard


import android.content.Context
import android.view.KeyEvent
import androidx.lifecycle.ViewModel
import com.example.keyboard_app.core.ime.ComposeImeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import android.media.AudioManager
import android.view.HapticFeedbackConstants
import android.view.View
import com.example.keyboard_app.core.constance.KeyViewType
import com.example.keyboard_app.core.constance.Keys
import com.example.keyboard_app.core.constance.Language
import com.example.keyboard_app.core.utilits.CharMapper

class KeyboardViewModal: ViewModel() {
    val abbroMapper = CharMapper(Keys.bnAbroMap)

    private val _crrKeyViewType = MutableStateFlow<KeyViewType>(KeyViewType.ENGLISH_LOAR)
    val crrKeyViewType: StateFlow<KeyViewType> = _crrKeyViewType
    private val _prvKeyViewType = MutableStateFlow<KeyViewType>(KeyViewType.ENGLISH_NUMBER_AND_SEMBLE)
    val prvKeyViewType: StateFlow<KeyViewType> = _prvKeyViewType


    private val _isVibrate = MutableStateFlow(true)
    val isVibrate:StateFlow<Boolean> = _isVibrate

    private val _isSoundPlay = MutableStateFlow(true)
    val isSoundPlay:StateFlow<Boolean> = _isSoundPlay

    private val _row1 = MutableStateFlow(Keys.enNumbers);
    private val _row2 = MutableStateFlow(Keys.eng.slice(0..9));

    private val _row3 = MutableStateFlow(Keys.eng.slice(10..18));
    private val _row4 = MutableStateFlow(Keys.eng.slice(19..25));
    
    val row1:StateFlow<List<String>> = _row1
    val row2:StateFlow<List<String>> = _row2
    val row3:StateFlow<List<String>> = _row3
    val row4:StateFlow<List<String>> = _row4

    fun arrowKey() {
        when(_crrKeyViewType.value){
            KeyViewType.ENGLISH_UPPER -> {
                _crrKeyViewType.value = KeyViewType.ENGLISH_LOAR
                _prvKeyViewType.value = KeyViewType.ENGLISH_UPPER
                setEnglish(false)
            }
            KeyViewType.ENGLISH_LOAR -> {
                _crrKeyViewType.value = KeyViewType.ENGLISH_UPPER
                _prvKeyViewType.value = KeyViewType.ENGLISH_LOAR
                setEnglish(true)
            }
            KeyViewType.ENGLISH_NUMBER_AND_SEMBLE ->setEnglishSymbols()
            KeyViewType.ENGLISH_SEMBLE -> setEnglishNumberSymbols()
            KeyViewType.BANGLA_SCREEN_1 -> setBanglaScreen2()
            KeyViewType.BANGLA_SCREEN_2 -> setBanglaScreen1()
            KeyViewType.BANGLA_NUMBER_AND_SEMBLE -> setBanglaSymbols()
            KeyViewType.BANGLA_SEMBLE -> setBanglaNumberAndSymbols()
            else -> Unit
        }
    }

    fun toggleEnBn (len: Language){
            when(len) {
                Language.BN -> setBanglaScreen1()
                Language.EN -> {
                    _crrKeyViewType.value = KeyViewType.ENGLISH_LOAR
                    setEnglish(false)
                }
            }
    }

    fun numKey() {
        when (_crrKeyViewType.value) {
            KeyViewType.ENGLISH_LOAR -> {
                setEnglishNumberSymbols()
                _prvKeyViewType.value = KeyViewType.ENGLISH_LOAR
            }
            KeyViewType.ENGLISH_UPPER -> {
                setEnglishNumberSymbols()
                _prvKeyViewType.value = KeyViewType.ENGLISH_UPPER
            }

            KeyViewType.BANGLA_SCREEN_1 -> {
                setBanglaNumberAndSymbols()
                _prvKeyViewType.value = KeyViewType.BANGLA_SCREEN_1
            }
            KeyViewType.BANGLA_SCREEN_2 -> {
                setBanglaNumberAndSymbols()
                _prvKeyViewType.value = KeyViewType.BANGLA_SCREEN_2
            }

            KeyViewType.ENGLISH_NUMBER_AND_SEMBLE,KeyViewType.ENGLISH_SEMBLE, KeyViewType.BANGLA_SEMBLE, KeyViewType.BANGLA_NUMBER_AND_SEMBLE  -> {
                when (_prvKeyViewType.value) {
                    KeyViewType.ENGLISH_LOAR -> {
                        setEnglish(false)
                        _prvKeyViewType.value = KeyViewType.ENGLISH_NUMBER_AND_SEMBLE
                        _crrKeyViewType.value = KeyViewType.ENGLISH_LOAR
                    }
                    KeyViewType.ENGLISH_UPPER -> {
                        setEnglish(true)
                        _prvKeyViewType.value = KeyViewType.ENGLISH_NUMBER_AND_SEMBLE
                        _crrKeyViewType.value = KeyViewType.ENGLISH_UPPER
                    }
                    KeyViewType.BANGLA_SCREEN_1 -> {
                      setBanglaScreen1()
                        _prvKeyViewType.value = KeyViewType.BANGLA_NUMBER_AND_SEMBLE
                    }
                    KeyViewType.BANGLA_SCREEN_2 -> {
                        setBanglaScreen2()
                        _prvKeyViewType.value = KeyViewType.BANGLA_NUMBER_AND_SEMBLE
                    }
                    else -> Unit
                }
            }
            else -> Unit
        }
    }

    fun type (ime: ComposeImeService?, char: String) {
        when(crrKeyViewType.value) {
            KeyViewType.BANGLA_SCREEN_1, KeyViewType.BANGLA_SCREEN_2 -> typeBn(ime, char)
            else -> ime?.currentInputConnection?.commitText(char,1)
            //else -> typeObro(ime, char)
        }
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
    fun sound(context: Context ?= null ) {
        if(context == null) {
            return
        }
        if(isSoundPlay.value) {
            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            audioManager.playSoundEffect(AudioManager.FX_KEY_CLICK, 1f)
        }
    }

    fun vibrate(view: View ?= null) {
        if(view == null) {
            return
        }
        if(isVibrate.value) {
            view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
    }

    private fun setEnglishNumberSymbols() {
        _row2.value = Keys.enNumbers.slice(0..9)
        _row3.value = Keys.symbols.slice(0..9)
        _row4.value = Keys.symbols.slice(10..16)
        _crrKeyViewType.value = KeyViewType.ENGLISH_NUMBER_AND_SEMBLE
    }

    private fun setEnglishSymbols() {
        _row2.value = Keys.symbols.slice(17..26)
        _row3.value = Keys.symbols.slice(27..36)
        _row4.value = Keys.symbols.slice(37..43)
        _crrKeyViewType.value = KeyViewType.ENGLISH_SEMBLE
    }
    private fun setEnglish(isUpper: Boolean) {
        _row2.value = if (isUpper) Keys.engUpper.slice(0..9) else Keys.eng.slice(0..9)
        _row3.value = if (isUpper) Keys.engUpper.slice(10..18) else Keys.eng.slice(10..18)
        _row4.value = if (isUpper) Keys.engUpper.slice(19..25) else Keys.eng.slice(19..25)
    }

    private fun setBanglaScreen1() {
        _crrKeyViewType.value = KeyViewType.BANGLA_SCREEN_1
        _row2.value = Keys.bn.slice(0..9)
        _row3.value = Keys.bn.slice(10..18)
        _row4.value = Keys.bn.slice(19..25)
    }
    private fun setBanglaScreen2() {
        _crrKeyViewType.value = KeyViewType.BANGLA_SCREEN_2
        _row2.value = Keys.bn.slice(26..35)
        _row3.value = Keys.bn.slice(36..44)
        _row4.value = Keys.bn.slice(45..51)
    }

    private fun  setBanglaNumberAndSymbols () {
        _row2.value = Keys.bnNumbers.slice(0..9)
        _row3.value = Keys.bn.slice(52..60)
        _row4.value = Keys.bn.slice(61..67)
        _crrKeyViewType.value = KeyViewType.BANGLA_NUMBER_AND_SEMBLE
    }
    private fun  setBanglaSymbols () {
        _row2.value = Keys.symbols.slice(0..9)
        _row3.value = Keys.symbols.slice(27..36)
        _row4.value = Keys.symbols.slice(37..43)
        _crrKeyViewType.value = KeyViewType.BANGLA_SEMBLE
    }

    private fun typeBn (ime: ComposeImeService?, char: String) {
        val displayValue = Keys.bnMap[char];
        if(displayValue !== null) {
            ime?.currentInputConnection?.commitText(displayValue,1)
            return
        }
        ime?.currentInputConnection?.commitText(char,1)
    }


    fun typeObro(ime: ComposeImeService?, input: String) {
        val ic = ime?.currentInputConnection ?: return
        abbroMapper.applyDecision(ic,  input)
    }

}