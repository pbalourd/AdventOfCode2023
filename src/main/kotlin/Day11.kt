import java.io.File
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.max

fun main(args: Array<String>) {
    val image = File("src/main/kotlin/input11.txt").readLines()
        .map { line -> line.toMutableList() }
        .toMutableList()

    val emptyRows = mutableListOf<Int>()
    for (y in image.indices){
        if (image[y].all { it == '.' }) {
            emptyRows.add(y)
        }
    }

    val emptyColumns = mutableListOf<Int>()
    for (x in image[0].indices) {
        if (image.map { line -> line[x] }.all { it == '.' }) {
            emptyColumns.add(x)
        }
    }

    val galaxies = mutableListOf<Pair<Int, Int>>()
    for (y in image.indices) {
        for (x in image[0].indices) {
            if (image[y][x] == '#') galaxies.add(Pair(y, x))
        }
    }

    val expansionSize1 = 1L
    val expansionSize2 = 1000000L - 1
    var sumShortestPath1 = 0L
    var sumShortestPath2 = 0L
    for (i in galaxies.indices) {
        for (j in i + 1..galaxies.lastIndex) {
            val expandedColumns = emptyColumns.count {
                it > min(galaxies[i].second, galaxies[j].second)
                        && it < max(galaxies[i].second, galaxies[j].second)
            }.toLong()
            val expandedRows = emptyRows.count {
                it > min(galaxies[i].first, galaxies[j].first)
                        && it < max(galaxies[i].first, galaxies[j].first)
            }.toLong()
            val path = abs(galaxies[i].second - galaxies[j].second) + abs(galaxies[i].first - galaxies[j].first)
            sumShortestPath1 += path + expandedRows * expansionSize1 + expandedColumns * expansionSize1
            sumShortestPath2 += path + expandedRows * expansionSize2 + expandedColumns * expansionSize2
        }
    }
    println(sumShortestPath1)
    // Solution 9521550

    println(sumShortestPath2)
    // Solution 298932923702
}

fun printImage(image: List<List<Char>>) {
    for (y in image.indices.reversed()) {
        println(image[y].joinToString(""))
    }
}

/*
    val image = File("src/main/kotlin/input11.txt").readLines()
        .map { line -> line.toMutableList() }
        .toMutableList()

    val emptyRows = mutableListOf<Int>()
    for (y in image.indices){
        if (image[y].all { it == '.' }) {
            emptyRows.add(y)
        }
    }
    for (y in emptyRows.reversed()) {
        val r = MutableList(image[0].size) { '.' }
        image.add(y, r)
    }

    val emptyColumns = mutableListOf<Int>()
    for (x in image[0].indices) {
        if (image.map { line -> line[x] }.all { it == '.' }) {
            emptyColumns.add(x)
        }
    }
    for (x in emptyColumns.reversed()) {
        for (y in image.indices) {
            image[y].add(x, '.')
        }
    }

    val galaxies = mutableListOf<Pair<Int, Int>>()
    for (y in image.indices) {
        for (x in image[0].indices) {
            if (image[y][x] == '#') galaxies.add(Pair(y, x))
        }
    }

    var sumShortestPaths = 0L
    for (i in galaxies.indices) {
        for (j in i + 1..galaxies.lastIndex) {
            sumShortestPaths += abs(galaxies[i].second - galaxies[j].second) + abs(galaxies[i].first - galaxies[j].first)
        }
    }
    println(sumShortestPaths)
    // Solution 9521550
 */