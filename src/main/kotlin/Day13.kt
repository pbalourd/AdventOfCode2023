import java.io.File
import kotlin.math.min

fun main(args: Array<String>) {
    val listOfGrids = File("src/main/kotlin/input13.txt").readText()
        .split("\\R{2}".toRegex())
        .map { str -> str.split("\\R".toRegex())
            .map { line -> line.toMutableList() }
        }

    var sum = 0
    for (i in listOfGrids.indices) {
        val c = findMirrorColumn(listOfGrids, i)
        val d = findMirrorRow(listOfGrids, i)
        if (c != -1) sum += c
        else if (d != -1) sum += d * 100
    }

    println(sum)
    // Solution 37975

    sum = 0
    for (p in listOfGrids.indices) {
        val c = repairSmudgeColumn(listOfGrids, p)
        val d = repairSmudgeRow(listOfGrids, p)
        if (c != -1) sum += c
        else if (d != -1) sum += d * 100
    }

    println(sum)
    // Solution 32497
}

fun findMirrorColumn(listOfGrids: List<List<List<Char>>>, pattern: Int): Int {
    var mirrorColumn = -1
    horizontalLoop@ for (x in 1..<listOfGrids[pattern][0].size) {
        for (y in 0..<listOfGrids[pattern].size) {
            val w = min(x, listOfGrids[pattern][0].size - x)
            for (k in 1..w) {
                if (listOfGrids[pattern][y][x - k] != listOfGrids[pattern][y][x + k - 1]) continue@horizontalLoop
            }
        }
        mirrorColumn = x
    }
    return mirrorColumn
}

fun findMirrorRow(listOfGrids: List<List<List<Char>>>, pattern: Int): Int {
    var mirrorRow = -1
    verticalLoop@ for (y in 1..<listOfGrids[pattern].size) {
        for (x in 0..<listOfGrids[pattern][0].size) {
            val l = min(y, listOfGrids[pattern].size - y)
            for (k in 1..l) {
                if (listOfGrids[pattern][y - k][x] != listOfGrids[pattern][y + k - 1][x]) continue@verticalLoop
            }
        }
        mirrorRow = y
    }
    return mirrorRow
}

fun repairSmudgeColumn(listOfGrids: List<List<List<Char>>>, pattern: Int): Int {
    for (x in 1..<listOfGrids[pattern][0].size) {
        var diffs = 0
        for (y in 0..<listOfGrids[pattern].size) {
            val w = min(x, listOfGrids[pattern][0].size - x)
            for (k in 1..w) {
                if (listOfGrids[pattern][y][x - k] != listOfGrids[pattern][y][x + k - 1]) {
                    diffs++
                }
            }
        }
        if (diffs == 1) return x
    }

    return -1
}

fun repairSmudgeRow(listOfGrids: List<List<List<Char>>>, pattern: Int): Int {
    for (y in 1..<listOfGrids[pattern].size) {
        var diffs = 0
        for (x in 0..<listOfGrids[pattern][0].size) {
            val l = min(y, listOfGrids[pattern].size - y)
            for (k in 1..l) {
                if (listOfGrids[pattern][y - k][x] != listOfGrids[pattern][y + k - 1][x]) {
                    diffs++
                }
            }
        }
        if (diffs == 1) return y
    }

    return -1
}