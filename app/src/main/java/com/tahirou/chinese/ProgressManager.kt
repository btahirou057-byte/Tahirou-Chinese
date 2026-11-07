
package com.tahirou.chinese

import android.content.Context

object ProgressManager {

    private const val PREFS = "tahirou_progress"

    private fun preferences(context: Context) =
        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )

    fun getLearnedWords(context: Context): Int {
        return preferences(context)
            .getInt("learned_words", 0)
    }

    fun addLearnedWord(context: Context) {
        val prefs = preferences(context)
        val count = prefs.getInt("learned_words", 0)
        prefs.edit()
            .putInt("learned_words", count + 1)
            .apply()
    }

    fun getCorrectAnswers(context: Context): Int {
        return preferences(context)
            .getInt("correct_answers", 0)
    }

    fun addCorrectAnswer(context: Context) {
        val prefs = preferences(context)
        val count = prefs.getInt("correct_answers", 0)
        prefs.edit()
            .putInt("correct_answers", count + 1)
            .apply()
    }

    fun getCompletedLessons(context: Context): Int {
        return preferences(context)
            .getInt("completed_lessons", 0)
    }

    fun completeLesson(context: Context) {
        val prefs = preferences(context)
        val count = prefs.getInt("completed_lessons", 0)
        prefs.edit()
            .putInt("completed_lessons", count + 1)
            .apply()
    }
}
