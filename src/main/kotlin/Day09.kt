import java.io.File

fun main(args: Array<String>) {
    val history = File("src/main/kotlin/input09.txt").readLines()
        .map { line ->
            line.split(" ")
                .map { it.toInt() }
        }

    var totalSum1 = 0L
    var totalSum2 = 0L
    for (i in history.indices) {
        var singleValueHistory = history[i]
        val beginNumber = mutableListOf<Int>()
        beginNumber.add(singleValueHistory[0])
        var sum = singleValueHistory[singleValueHistory.lastIndex].toLong()
        while (!singleValueHistory.all { it == 0 }) {
            val temp = mutableListOf<Int>()
            for (j in 1..singleValueHistory.lastIndex) temp.add(singleValueHistory[j] - singleValueHistory[j - 1])
            singleValueHistory = temp
            sum += singleValueHistory[singleValueHistory.lastIndex]
            beginNumber.add(singleValueHistory[0])
        }
        totalSum1 += sum
        var k = 0
        for (d in beginNumber.lastIndex - 1 downTo 0) k = beginNumber[d] - k
        totalSum2 += k
    }

    println(totalSum1)
    // Solution 1980437560

    println(totalSum2)
    // Solution 977
}