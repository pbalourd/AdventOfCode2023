import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/kotlin/input06.txt").readLines()
    val listTimes = input[0]
        .substringAfter("Time:")
        .trim()
        .split("\\s+".toRegex())
        .map { it.toInt() }

    val listDistance = input[1]
        .substringAfter("Distance:")
        .trim()
        .split("\\s+".toRegex())
        .map { it.toInt() }

    val numOfRaces = listTimes.size
    var product = 1
    for (i in 0..<numOfRaces) {
        var count = 0
        for (t in 1..<listTimes[i]) {
            val d = (listTimes[i] - t) * t
            if (d > listDistance[i]) count++
        }
        if (count > 0) product *= count
    }
    println(product)

    // Solution 861300

    val oneTime = listTimes
        .joinToString("") { it.toString() }
        .toLong()

    val oneDistance =listDistance
        .joinToString("") { it.toString() }
        .toLong()

    var count = 0L
    for (t in 1..<oneTime) {
        val d = (oneTime - t) * t
        if (d > oneDistance) count++
    }

    println(count)
    //Solution 28101347
}