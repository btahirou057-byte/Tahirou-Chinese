
package com.tahirou.chinese

enum class AppLanguage(
    val code: String,
    val displayName: String
) {
    FRENCH("fr", "Français"),
    ARABIC("ar", "العربية")
}

object LanguageSettings {

    private const val PREFS_NAME = "tahirou_preferences"
    private const val LANGUAGE_KEY = "selected_language"

    fun saveLanguage(
        context: android.content.Context,
        language: AppLanguage
    ) {
        context.getSharedPreferences(
            PREFS_NAME,
            android.content.Context.MODE_PRIVATE
        ).edit()
            .putString(LANGUAGE_KEY, language.code)
            .apply()
    }

    fun getLanguage(
        context: android.content.Context
    ): AppLanguage {
        val code = context.getSharedPreferences(
            PREFS_NAME,
            android.content.Context.MODE_PRIVATE
        ).getString(LANGUAGE_KEY, "fr")

        return if (code == "ar") {
            AppLanguage.ARABIC
        } else {
            AppLanguage.FRENCH
        }
    }
}
