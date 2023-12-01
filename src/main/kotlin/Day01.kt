import java.io.File

fun main(args: Array<String>) {
    val lines = File("src/main/kotlin/input01.txt").readLines()

    val letters = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val digits = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    val digitsLetters = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val values1 = mutableListOf<Int>()
    val values2 = mutableListOf<Int>()

    for (line in lines) {
        val allDigits = findAll(line, digits)
        val allDigitsLetters = findAll(line, digitsLetters)

        var n1 = 10 * allDigits[0].toInt()
        n1 += allDigits[allDigits.lastIndex].toInt()

        values1.add(n1)

        var n2 = 10 * if (allDigitsLetters[0] in letters) (letters.indexOf(allDigitsLetters[0]) + 1)
        else allDigitsLetters[0].toInt()

        n2 += if (allDigitsLetters[allDigitsLetters.lastIndex] in letters) (letters.indexOf(allDigitsLetters[allDigitsLetters.lastIndex]) + 1)
        else allDigitsLetters[allDigitsLetters.lastIndex].toInt()

        values2.add(n2)
    }

    println(values1.sum())
    // Solution 56506
    println(values2.sum())
    // Solution 56017
}

fun findAll(str: String, subStrs: List<String>): List<String> {
    val result = mutableListOf<String>()
    for (i in str.indices) {
        for (s in subStrs) {
            if (str.substring(i).startsWith(s)) {
                result.add(s)
                break
            }
        }
    }

    return result.toList()
}
