package com.example.keyboard_app.core.utilits

import android.os.Build
import android.view.inputmethod.InputConnection
import com.example.keyboard_app.core.constance.MapDecision

class CharMapper(
    private val map: Map<String, String>
) {
    private val buffer = StringBuilder()
    private var candidates: List<String> = emptyList()

    private fun reset() {
        buffer.clear()
        candidates = emptyList()
    }

    /** Feed one key (as String). Returns what to do next. */
   private fun feed(input: String): MapDecision {
        val proposed = buffer.toString() + input

        // If we already have candidates, check if they still match with proposed; otherwise restart.
        val stillMatches = candidates.any { it.startsWith(proposed) }
        if (!stillMatches) {
           reset()
        }

        // Update buffer after potential reset.
        buffer.append(input)
        val prefix = buffer.toString()

        // Find fresh candidates for this prefix.
        candidates = map.keys.filter { it.startsWith(prefix) }

        if (candidates.isEmpty()) {
            // No mapping path → emit raw and reset.
            reset()
            return MapDecision.NoMatch(input)
        }

        if (candidates.size == 1) {
            val key = candidates.first()
            val isFull = key.length == prefix.length
            val mapped = map[key] ?: key
            if (isFull) {
                // Reached a full match. If this took >1 keystrokes, we already emitted something previously,
                // so we should replace the previous code point.
                val deleteBefore = if (prefix.length > 1) 1 else 0
                reset()
                return MapDecision.Final(mapped, deleteBefore)
            }
        }

        // Still ambiguous (multiple candidates or single but not full) → emit per-char mapping for this input
        // so user sees progressive output, and keep buffering.
        val emit = map[input] ?: input
        return MapDecision.Partial(emit, buffer.toString())
    }


    fun applyDecision(ic: InputConnection, input: String) {
        val d = feed(input)
        when (d) {
            is MapDecision.NoMatch -> {
                ic.commitText(d.raw, 1)
            }
            is MapDecision.Partial -> {
                ic.commitText(d.emit, 1)
            }
            is MapDecision.Final -> {
                ic.beginBatchEdit()
                try {
                    if (d.deleteBefore > 0) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            ic.deleteSurroundingTextInCodePoints(d.deleteBefore, 0)
                        } else {
                            ic.deleteSurroundingText(d.deleteBefore, 0)
                        }
                    }
                    ic.commitText(d.mapped, 1)
                } finally {
                    ic.endBatchEdit()
                }
            }
        }
    }

}