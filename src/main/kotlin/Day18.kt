import java.io.File

data class Action(val dir: Char, val steps: Int, val color: String)

fun main(args: Array<String>) {
    val actions = File("src/main/kotlin/input18.txt").readLines()
        .map { line -> line.split(" ") }
        .map { Action(it[0].first(), it[1].toInt(), it[2].drop(2).dropLast(1)) }

    var y = 0
    var x = 0
    val loop = mutableListOf<Pair<Int, Int>>()
    for (action in actions) {
        when (action.dir) {
            'R' -> {
                for (i in 1..action.steps) { x++; loop.add(Pair(y, x)) }
            }
            'L' -> {
                for (i in 1..action.steps) { x--; loop.add(Pair(y, x)) }
            }
            'U' -> {
                for (i in 1..action.steps) { y--; loop.add(Pair(y, x)) }
            }
            'D' -> {
                for (i in 1..action.steps) { y++; loop.add(Pair(y, x)) }
            }
        }
    }

    val r = Pair(-79, -108)

    val inside = mutableListOf<Pair<Int, Int>>()
    val queue = mutableListOf<Pair<Int, Int>>()
    queue.add(r)
    while (queue.isNotEmpty()) {
        val p = queue.removeAt(0)
        if (Pair(p.first - 1, p.second) !in loop && Pair(p.first - 1, p.second) !in inside) {
            inside.add(Pair(p.first - 1, p.second))
            queue.add(Pair(p.first - 1, p.second))
        }
        if (Pair(p.first + 1, p.second) !in loop && Pair(p.first + 1, p.second) !in inside) {
            inside.add(Pair(p.first + 1, p.second))
            queue.add(Pair(p.first + 1, p.second))
        }
        if (Pair(p.first, p.second - 1) !in loop && Pair(p.first, p.second - 1) !in inside) {
            inside.add(Pair(p.first, p.second - 1))
            queue.add(Pair(p.first, p.second - 1))
        }
        if (Pair(p.first, p.second + 1) !in loop && Pair(p.first, p.second + 1) !in inside) {
            inside.add(Pair(p.first, p.second + 1))
            queue.add(Pair(p.first, p.second + 1))
        }
    }

    println(inside.size + loop.size)
    // Solution 38188
}

fun printLoop(loop: MutableList<Pair<Int, Int>>) {
    val minYPos = loop.minOf { it.first }
    val maxYPos = loop.maxOf { it.first }
    val minXPos = loop.minOf { it.second }
    val maxXPos = loop.maxOf { it.second }

    for (y in minYPos..maxYPos) {
        for (x in minXPos..maxXPos) {
            if (Pair(y, x) in loop) print("#") else print(".")
        }
        println()
    }
}