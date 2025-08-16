package com.example.keyboard_app.core.constance

sealed class KeyViewType{
    object ENGLISH_LOAR: KeyViewType()
    object ENGLISH_UPPER: KeyViewType()
    object ENGLISH_NUMBER_AND_SEMBLE: KeyViewType()

    object ENGLISH_SEMBLE: KeyViewType()

    object BANGLA_SCREEN_1: KeyViewType()
    object BANGLA_SCREEN_2: KeyViewType()
    object BANGLA_NUMBER_AND_SEMBLE: KeyViewType()
    object BANGLA_SEMBLE: KeyViewType()
}