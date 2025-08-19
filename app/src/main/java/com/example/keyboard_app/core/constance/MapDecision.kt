package com.example.keyboard_app.core.constance

sealed class MapDecision {
    data class NoMatch(val raw: String) : MapDecision()            // emit raw input
    data class Partial(val emit: String, val buffer: String) : MapDecision() // emit single mapped char, keep buffering
    data class Final(val mapped: String, val deleteBefore: Int) : MapDecision() // delete N, then commit mapped
}