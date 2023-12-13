import java.io.File

data class Pos(val y: Int, val x: Int)

val north = listOf('|', '7', 'F')
val south = listOf('|', 'L', 'J')
val east = listOf('-', 'J', '7')
val west = listOf('-', 'L', 'F')

val tiles = listOf(
    Triple('|','N','S'),
    Triple('-','E','W'),
    Triple('L','N','E'),
    Triple('J','N','W'),
    Triple('7','S','W'),
    Triple('F','S','E'),
)

fun main(args: Array<String>) {
    val input = File("src/main/kotlin/input10.txt").readLines()

    val sY = input.indexOf(input.first { line -> line.contains('S') })
    val sX = input[sY].indexOf('S')

    val grid = input.map { line -> line.toList() }

    val loop = mutableListOf<Pos>()
    loop.add(Pos(sY, sX))
    loop.add(findStartDirection(sY, sX, grid))


    var p = findPath(loop[1], loop[0], grid)
    while (p != loop[0]) {
        loop.add(p)
        p = findPath(loop[loop.lastIndex], loop[loop.lastIndex - 1], grid)
    }
    println(loop.size / 2)
    // Solution 6757
}

fun findStartDirection(sY: Int, sX: Int, grid: List<List<Char>>): Pos {
    return if (grid[sY - 1][sX] in north) Pos(sY - 1, sX)
    else if (grid[sY + 1][sX] in south) Pos(sY + 1, sX)
    else if (grid[sY][sX + 1] in east) Pos(sY, sX + 1)
    else Pos(sY, sX + 1)
}

fun findPath(current: Pos, previous: Pos, grid: List<List<Char>>): Pos {
    val c = grid[current.y][current.x]
    val tile = tiles.first { it.first == c }
    val p = newPos(current, tile.second)
    return if (p != previous) p else newPos(current, tile.third)
}

fun newPos(current: Pos, direction: Char): Pos {
    return when(direction) {
        'N' -> Pos(current.y - 1, current.x)
        'S' -> Pos(current.y + 1, current.x)
        'W' -> Pos(current.y, current.x - 1)
        else -> Pos(current.y, current.x + 1)
    }
}