
package com.tahirou.chinese

import android.content.Context

object VocabularyRepository {

    fun loadWords(context: Context): List<ChineseWord> {
        return try {
            context.assets.open("vocabulary.csv")
                .bufferedReader(Charsets.UTF_8)
                .useLines { lines ->
                    lines.drop(1)
                        .filter { it.isNotBlank() }
                        .mapNotNull { line ->
                            val parts = line.split("|")
                            if (parts.size >= 4) {
                                ChineseWord(
                                    chinese = parts[0].trim(),
                                    pinyin = parts[1].trim(),
                                    french = parts[2].trim(),
                                    arabic = parts[3].trim()
                                )
                            } else {
                                null
                            }
                        }
                        .toList()
                }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
