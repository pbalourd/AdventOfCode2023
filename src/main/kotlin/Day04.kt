import java.io.File

fun main(args: Array<String>) {
    val cards = File("src/main/kotlin/input04.txt").readLines()
        .map { it.substringAfter(": ").split(" | ") }

    val splitter = "\\s+".toRegex()
    var totalPoints = 0L
    val numOfCopies = MutableList(cards.size) { 1 }
    for ((index, card) in cards.withIndex()) {
        val numbers = card[0].split(splitter)
        val winningNumbers = card[1].split(splitter)

        var points = 0L
        var wins = 0
        for (n in numbers) {
            if (n in winningNumbers) {
                if (points == 0L) points = 1L else points *= 2
                wins++
            }
        }

        for (i in 1..wins) numOfCopies[index + i] += numOfCopies[index]
        totalPoints += points
    }

    println(totalPoints)
    // Solution 18653

    println(numOfCopies.sum())
    // Solution 5921508
}