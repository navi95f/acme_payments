package data

import java.io.File

class FileReader {

        fun getContent(fileName: String): ArrayList<String> {
            val inputStream = File("src/main/resources/$fileName").inputStream()
            val lineList = ArrayList<String>()

            inputStream.bufferedReader().forEachLine { lineList.add(it) }
            return lineList
        }
}