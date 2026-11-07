
package com.tahirou.chinese

data class ChineseLesson(
    val id: Int,
    val title: String,
    val level: String,
    val frenchExplanation: String,
    val arabicExplanation: String,
    val chineseExample: String,
    val pinyin: String,
    val translation: String
)

object LessonManager {

    val lessons = listOf(

        ChineseLesson(
            1,
            "Salutations",
            "Débutant",
            "Apprenez à saluer en chinois.",
            "تَعَلَّمْ كَيْفِيَّةَ التَّحِيَّةِ بِاللُّغَةِ الصِّينِيَّةِ.",
            "你好",
            "Nǐ hǎo",
            "Bonjour"
        ),

        ChineseLesson(
            2,
            "Remercier",
            "Débutant",
            "Apprenez à dire merci.",
            "تَعَلَّمْ كَيْفَ تَقُولُ شُكْرًا.",
            "谢谢",
            "Xièxie",
            "Merci"
        ),

        ChineseLesson(
            3,
            "Se présenter",
            "Débutant",
            "Apprenez à vous présenter.",
            "تَعَلَّمْ كَيْفَ تُعَرِّفُ بِنَفْسِكَ.",
            "我叫...",
            "Wǒ jiào...",
            "Je m'appelle..."
        )
    )

    fun getLesson(id: Int): ChineseLesson? {
        return lessons.find { it.id == id }
    }
}
