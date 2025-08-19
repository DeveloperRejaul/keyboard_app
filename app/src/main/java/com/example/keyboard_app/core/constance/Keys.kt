package com.example.keyboard_app.core.constance
object Keys {
    val bn = listOf(
        "ঙ", "য","ড","প","ট","চ","জ","হ","গ","ড়",CharactersId.REE,CharactersId.ROSHO_KAR,CharactersId.ROSHI_KAR,
        "ব",CharactersId.JOKTTO,CharactersId.AKAR,"ক","ত","দ",CharactersId.CHANDRO_BINDRO,CharactersId.O_KAR,CharactersId.A_KAR,"র","ন","স","ম",
        CharactersId.ONOSKAR,CharactersId.JOPOLA,"ঢ","ফ", "ঠ","ছ","ঝ","ঞ","ঘ", "ঢ়","ঋ", CharactersId.DIRGO_KAR ,CharactersId.DIRGI_KAR ,"ভ",CharactersId.REF,"অ",
        "খ","থ","ধ",CharactersId.BISHORHO,CharactersId.AO_KAR, CharactersId.OI_KAR, CharactersId.TROPOLA,"ণ","ষ","শ",
        "ৎ","আ","ই","ঈ","উ","ঊ","ঐ","ও","ঔ","এ","ঐ","ল","য়","।",",","."
    )

    val bnMap = mapOf(
        CharactersId.JOKTTO to "্",
        CharactersId.ROSHO_KAR to "ু",
        CharactersId.ROSHI_KAR to "ি",
        CharactersId.AKAR to "া",
        CharactersId.CHANDRO_BINDRO to "ঁ",
        CharactersId.O_KAR to "ো",
        CharactersId.A_KAR to "ে",
        CharactersId.ONOSKAR to "ং",
        CharactersId.JOPOLA to "্য",
        CharactersId.DIRGO_KAR to "ূ",
        CharactersId.DIRGI_KAR to "ী",
        CharactersId.BISHORHO to "ঃ",
        CharactersId.AO_KAR to "ৌ",
        CharactersId.OI_KAR to "ৈ",
        CharactersId.REF to "র্",
        CharactersId.TROPOLA to "্র",
    )

    val bnAbroMap = mapOf(
        "a" to "অ",
        "ah" to "আ",
        "i" to "ই",
        "ii" to "ঈ",
        "k" to "ক",
        "ki" to "কি"
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
