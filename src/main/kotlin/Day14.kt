import java.io.File

fun main(args: Array<String>) {
    val platform = File("src/main/kotlin/input14.txt").readLines()
        .map { line -> line.toMutableList() }
        .toMutableList()

    tiltPlatform(platform, 'N')
    println(platformLoad(platform))
    // Solution 110407

    val platformsList = mutableListOf<String>()
    var firstOccurrence = 0
    var secondOccurrence = 0
    var cycleToBreak = 0
    var tempStr = ""
    for (i in 1..Int.MAX_VALUE) {
        tiltPlatform(platform, 'N')
        tiltPlatform(platform, 'W')
        tiltPlatform(platform, 'S')
        tiltPlatform(platform, 'E')
        val p = platform.joinToString("") { it.joinToString("") }
        if (p in platformsList) {
            if (firstOccurrence == 0) {
                firstOccurrence = i
                tempStr = p
            }
            else {
                if (p == tempStr) {
                    secondOccurrence = i
                    cycleToBreak = secondOccurrence + (1_000_000_000 - firstOccurrence) % (secondOccurrence - firstOccurrence)
                }
            }
        } else platformsList.add(p)
        if (cycleToBreak == i) break
    }

    println(platformLoad(platform))
    // Solution 87273
}

private fun tiltPlatform(platform: MutableList<MutableList<Char>>, direction: Char) {
    val yLastIndex = platform.lastIndex
    val xLastIndex = platform[0].lastIndex
    when (direction) {
        'N' -> {
            for (y in 1..yLastIndex) {
                for (x in 0..xLastIndex) {
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
        }
        'S' -> {
            for (y in (yLastIndex - 1)downTo 0) {
                for (x in 0..xLastIndex) {
                    if (platform[y][x] == 'O') {
                        var movable = true
                        var yMove = y + 1
                        while (movable && yMove <= yLastIndex) {
                            if (platform[yMove][x] != '.') {
                                movable = false
                            } else {
                                platform[yMove][x] = 'O'
                                platform[yMove - 1][x] = '.'
                                yMove++
                            }
                        }
                    }
                }
            }
        }
        'W' -> {
            for (x in 1..xLastIndex) {
                for (y in 0..yLastIndex) {
                    if (platform[y][x] == 'O') {
                        var movable = true
                        var xMove = x - 1
                        while (movable && xMove >= 0) {
                            if (platform[y][xMove] != '.') {
                                movable = false
                            } else {
                                platform[y][xMove]= 'O'
                                platform[y][xMove + 1] = '.'
                                xMove--
                            }
                        }
                    }
                }
            }
        }
        'E' -> {
            for (x in (xLastIndex - 1) downTo  0) {
                for (y in 0..yLastIndex) {
                    if (platform[y][x] == 'O') {
                        var movable = true
                        var xMove = x + 1
                        while (movable && xMove <= xLastIndex) {
                            if (platform[y][xMove] != '.') {
                                movable = false
                            } else {
                                platform[y][xMove]= 'O'
                                platform[y][xMove - 1] = '.'
                                xMove++
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun platformLoad(platform: MutableList<MutableList<Char>>): Long {
    var load = 0L
    for (y in platform.indices) {
        for (x in 0..<platform[0].size) {
            if (platform[y][x] == 'O') load += platform.size - y
        }
    }
    return load
}

fun printPlatform(p: MutableList<MutableList<Char>>) {
    for (y in p.indices) {
        for (x in p[0].indices) {
            print(p[y][x])
        }
        println()
    }
}