package com.example.keyboard_app.core.constance
object Keys {

    /** Bangla independent vowels */
    val bnIndependent = listOf(
        "অ","আ","ই","ঈ","উ","ঊ","ঋ","এ","ঐ","ও","ঔ"
    )

    /** Bangla consonants */
    val bn = listOf(
        "ক","খ","গ","ঘ","ঙ",
        "চ","ছ","জ","ঝ","ঞ",
        "ট","ঠ","ড","ঢ","ণ",
        "ত","থ","দ","ধ","ন",
        "প","ফ","ব","ভ","ম",
        "য","র","ল","শ","ষ","স","হ",
        "ড়","ঢ়","য়"
    )

    val eng = ('a'..'z').map { it.toString() }
    val engUpper = ('A'..'Z').map { it.toString() }

    /** Common symbols */
    val symbols = listOf(
        "!", "@", "#", "$", "%", "^", "&", "*", "(", ")",
        "-", "_", "=", "+", "[", "]", "{", "}", ";", ":",
        "'", "\"", ",", ".", "/", "<", ">", "?", "|", "\\",
        "`", "~", "`", "~", "`", "~", "`", "~", "`",  "~",
        "`", "~", "`","`"
    )

    /** Bangla numbers */
    val bnNumbers = listOf("০","১","২","৩","৪","৫","৬","৭","৮","৯")
    /** Numbers (0–9) */
    val enNumbers = (0..9).map { it.toString() }
}
