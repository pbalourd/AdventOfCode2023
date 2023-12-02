import java.io.File

fun main(args: Array<String>) {
    val maxCubeNum = listOf(12, 13, 14)
    val cubes = mutableListOf<MutableList<Int>>()

    val games = File("src/main/kotlin/input02.txt").readLines()
        .map { it.substringAfter(": ").split(", ", "; ") }

    for (game in games) {
        val cubesNumbers = MutableList(3) { 0 }
        for (cubeSet in game) {
            val setInfo = cubeSet.split(" ")
            val numOfCubes = setInfo[0].toInt()
            when (setInfo[1]) {
                "red" -> if (numOfCubes > cubesNumbers[0]) cubesNumbers[0] = numOfCubes
                "green" -> if (numOfCubes > cubesNumbers[1]) cubesNumbers[1] = numOfCubes
                "blue" -> if (numOfCubes > cubesNumbers[2]) cubesNumbers[2] = numOfCubes
            }
        }
        cubes.add(cubesNumbers)
    }

    var sum = 0
    for ((i, rgb) in cubes.withIndex()) {
        if (rgb[0] <= maxCubeNum[0] && rgb[1] <= maxCubeNum[1] && rgb[2] <= maxCubeNum[2]) sum += i + 1
    }
    println(sum)
    // Solution 2505

    sum = 0
    for (rgb in cubes) {
        sum += rgb.reduce { acc, i -> acc * i }
    }
    println(sum)
    // Solution 70265
}