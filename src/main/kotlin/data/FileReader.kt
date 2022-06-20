package data

import java.io.File

class FileReader {

    /**
     * @param fileName -> name of the file to read
     * @return -> List containing every line of the file read
     *
     * Reads a specified file located in the /resources folder and returns a list of Strings with each line of the file
     * */
    fun getContent(fileName: String): ArrayList<String> {
        val inputStream = File("src/main/resources/$fileName").inputStream()
        val lineList = ArrayList<String>()
        inputStream.bufferedReader().forEachLine { lineList.add(it) }
        return lineList
    }
}