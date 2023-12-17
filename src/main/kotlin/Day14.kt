import java.io.File

fun main(args: Array<String>) {
    val platform = File("src/main/kotlin/input14.txt").readLines()
        .map { line -> line.toMutableList() }
        .toMutableList()

    val length = platform.size
    val width = platform[0].size

    for (y in 1..platform.lastIndex) {
        for (x in 0..<width) {
            if (platform[y][x] == 'O') {
                var movable = true
                var yMove = y - 1
                while (movable && yMove >= 0) {
                    if (platform[yMove][x] != '.') {
                        movable = false
                    } else {
                        platform[yMove][x] = 'O'
                        platform[yMove + 1][x] = '.'
                        yMove--
                    }
                }
            }
        }
    }

    var load = 0L
    for (y in platform.indices) {
        for (x in 0..<width) {
            if (platform[y][x] == 'O') load += length - y
        }
    }

    println(load)
    // Solution 110407
}

fun printPlatform(p: MutableList<MutableList<Char>>) {
    for (y in p.indices) {
        for (x in p[0].indices) {
            print(p[y][x])
        }
        println()
    }
}