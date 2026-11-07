
package com.tahirou.chinese

data class QuizQuestion(
    val question: String,
    val correctAnswer: String,
    val options: List<String>,
    val explanation: String
)

object QuizEngine {

    fun generateQuestion(
        words: List<ChineseWord>,
        language: AppLanguage
    ): QuizQuestion? {

        if (words.size < 4) return null

        val selected = words.random()

        val correctAnswer = when (language) {
            AppLanguage.FRENCH -> selected.french
            AppLanguage.ARABIC -> selected.arabic
        }

        val wrongAnswers = words
            .filter { it.chinese != selected.chinese }
            .map {
                when (language) {
                    AppLanguage.FRENCH -> it.french
                    AppLanguage.ARABIC -> it.arabic
                }
            }
            .filter { it != correctAnswer }
            .distinct()
            .shuffled()
            .take(3)

        if (wrongAnswers.size < 3) return null

        return QuizQuestion(
            question = selected.chinese,
            correctAnswer = correctAnswer,
            options = (wrongAnswers + correctAnswer).shuffled(),
            explanation = selected.pinyin
        )
    }

    fun checkAnswer(
        question: QuizQuestion,
        answer: String
    ): Boolean {
        return answer == question.correctAnswer
    }
}
