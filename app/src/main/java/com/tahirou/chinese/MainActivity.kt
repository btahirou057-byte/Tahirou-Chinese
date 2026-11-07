
package com.tahirou.chinese

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Locale

class MainActivity : ComponentActivity() {

    private var tts: TextToSpeech? = null
    private var ttsReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tts = TextToSpeech(this) { status ->
            ttsReady = status == TextToSpeech.SUCCESS
            if (ttsReady) {
                tts?.language = Locale.SIMPLIFIED_CHINESE
            }
        }

        setContent {
            MaterialTheme {
                TahirouChineseApp(
                    onSpeak = { word ->
                        if (ttsReady) {
                            tts?.speak(
                                word,
                                TextToSpeech.QUEUE_FLUSH,
                                null,
                                "chinese_word"
                            )
                        }
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}

data class ChineseWord(
    val chinese: String,
    val pinyin: String,
    val french: String,
    val arabic: String
)

@Composable
fun TahirouChineseApp(onSpeak: (String) -> Unit) {

    val words = listOf(
        ChineseWord("你好", "Nǐ hǎo", "Bonjour", "مَرْحَبًا"),
        ChineseWord("谢谢", "Xièxie", "Merci", "شُكْرًا"),
        ChineseWord("再见", "Zàijiàn", "Au revoir", "إِلَى اللِّقَاءِ"),
        ChineseWord("水", "Shuǐ", "Eau", "مَاءٌ"),
        ChineseWord("学习", "Xuéxí", "Étudier", "دِرَاسَةٌ")
    )

    var search by remember { mutableStateOf("") }

    val filteredWords = words.filter {
        it.chinese.contains(search) ||
        it.pinyin.contains(search, ignoreCase = true) ||
        it.french.contains(search, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🇨🇳 Tahirou Chinese") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Apprendre le chinois facilement",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("10 000 mots • Français • العربية")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                label = { Text("Rechercher un mot") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Vocabulaire chinois",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredWords) { word ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    word.chinese,
                                    style = MaterialTheme.typography.headlineMedium
                                )

                                Text("Pinyin : ${word.pinyin}")
                                Text("Français : ${word.french}")
                                Text("العربية : ${word.arabic}")
                            }

                            Button(
                                onClick = { onSpeak(word.chinese) }
                            ) {
                                Text("🔊")
                            }
                        }
                    }
                }
            }
        }
    }
}
