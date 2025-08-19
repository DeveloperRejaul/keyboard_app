package com.example.keyboard_app.core.constance
object Keys {
    val bn = listOf(
        "ঙ", "য","ড","প","ট","চ","জ","হ","গ","ড়",CharactersId.REE,CharactersId.ROSHO_KAR,CharactersId.ROSHI_KAR,"ব","্","া","ক","ত","দ","ঁ","ো","ে", "র","ন","স","ম",
        "ং","্য","ঢ","ফ", "ঠ","ছ","ঝ","ঞ","ঘ", "ঢ়","ঋ","ূ","ী","ভ","র্","অ","খ","থ","ধ","ঃ","ৌ","ৈ","্র","ণ","ষ","শ",
        "ৎ","আ","ই","ঈ","উ","ঊ","ঐ","ও","ঔ","এ","ঐ","ল","য়","।",",","."
    )

    val bnMap = mapOf<String, String>(
        CharactersId.REE to "ৃ",
        CharactersId.ROSHO_KAR to "ু",
        CharactersId.ROSHI_KAR to "ি",
    )

    val eng: List<String> = listOf("q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m")

    val engUpper = eng.map { it.uppercase() }

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
