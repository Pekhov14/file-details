package pekhov.filedetails

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.ui.Messages
import java.io.File
import java.text.SimpleDateFormat

class FileDetailsAction : AnAction() {
    private fun bytesToKilobytes(bytes: Long): Double {
        val kilobytes = bytes.toDouble() / 1024
        return "%.2f".format(kilobytes).toDouble()
    }

    private fun bytesToMegabytes(bytes: Long): Double {
        val megabytes = bytes.toDouble() / (1024 * 1024)
        return "%.2f".format(megabytes).toDouble()
    }

    override fun actionPerformed(e: AnActionEvent) {
        val dataContext = e.dataContext
        val virtualFile = LangDataKeys.VIRTUAL_FILE.getData(dataContext)

        if (virtualFile != null) {
            val file = File(virtualFile.path)
            val fileSizeInBytes = file.length()
            val creationTime = file.lastModified()

            val formattedTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(creationTime)
            val message = "File: ${file.name}\n" +
                    "Size: $fileSizeInBytes bytes\n" +
                    "Creation Time: $formattedTime"

            Messages.showInfoMessage(message, "File Details")
        }
    }
}