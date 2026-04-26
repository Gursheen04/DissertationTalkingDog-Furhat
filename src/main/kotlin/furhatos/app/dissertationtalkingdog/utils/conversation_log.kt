package furhatos.app.dissertationtalkingdog.utils

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Transcript {

    private val file = File("transcript.txt")
    private val timestampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun log(speaker: String, text: String) {
        val timestamp = LocalDateTime.now().format(timestampFormat)
        file.appendText("[$timestamp] $speaker: $text\n")
    }
}