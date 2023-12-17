import java.io.File
import kotlin.math.max

data class Beam(val y: Int, val x: Int, val direction: Char)

fun main(args: Array<String>) {
    val grid = File("src/main/kotlin/input16.txt").readLines()
        .map { line -> line.toList() }

    val length = grid.size
    val width = grid[0].size

    val energized = countEnergizedTiles(0, 0, 'R', grid, length, width)

    println(energized)
    //Solution 7472

    var maxEnergizedTiles = 0
    for (y in 0..<length) {
        val e = max(countEnergizedTiles(y, 0, 'R', grid, length, width),
            countEnergizedTiles(y, width - 1, 'L', grid, length, width))
        if (e > maxEnergizedTiles) maxEnergizedTiles = e
    }
    for (x in 0..<width) {
        val e = max(countEnergizedTiles(0, x, 'D', grid, length, width),
            countEnergizedTiles(length - 1, x, 'U', grid, length, width))
        if (e > maxEnergizedTiles) maxEnergizedTiles = e
    }
    println(maxEnergizedTiles)
    // Solution 7716
}

private fun countEnergizedTiles(y: Int, x: Int, d: Char, grid: List<List<Char>>, length: Int, width: Int): Int {
    val queue = mutableListOf<Beam>()
    queue.add(Beam(y, x, d))
    val energizedTiles = mutableSetOf<Beam>()
    energizedTiles.add(Beam(y, x, d))
    while (queue.isNotEmpty()) {
        val beam = queue.removeAt(0)
        val tile = grid[beam.y][beam.x]
        when (beam.direction) {
            'R' -> {
                when (tile) {
                    '|' -> {
                        radiateBeam(length, width, queue, energizedTiles, Beam(beam.y - 1, beam.x, 'U'))
                        radiateBeam(length, width, queue, energizedTiles, Beam(beam.y + 1, beam.x, 'D'))
                    }
                    '/' -> radiateBeam(length, width, queue, energizedTiles, Beam(beam.y - 1, beam.x, 'U'))
                    '\\' -> radiateBeam(length, width, queue, energizedTiles, Beam(beam.y + 1, beam.x, 'D'))
                    '-', '.' -> radiateBeam(length, width, queue, energizedTiles, Beam(beam.y, beam.x + 1, 'R'))
                }
            }

            'L' -> {
                when (tile) {
                    '|' -> {
                        radiateBeam(length, width, queue, energizedTiles, Beam(beam.y + 1, beam.x, 'D'))
                        radiateBeam(length, width, queue, energizedTiles, Beam(beam.y - 1, beam.x, 'U'))
                    }
                    '/' -> radiateBeam(length, width, queue, energizedTiles, Beam(beam.y + 1, beam.x, 'D'))
                    '\\' -> radiateBeam(length, width, queue, energizedTiles, Beam(beam.y - 1, beam.x, 'U'))
                    '-', '.' -> radiateBeam(length, width, queue, energizedTiles, Beam(beam.y, beam.x - 1, 'L'))
                }
            }

            'U' -> {
                when (tile) {
                    '-' -> {
                        radiateBeam(length, width, queue, energizedTiles, Beam(beam.y, beam.x + 1, 'R'))
                        radiateBeam(length, width, queue, energizedTiles, Beam(beam.y, beam.x - 1, 'L'))
                    }
                    '/' -> radiateBeam(length, width, queue, energizedTiles, Beam(beam.y, beam.x + 1, 'R'))
                    '\\' -> radiateBeam(length, width, queue, energizedTiles, Beam(beam.y, beam.x - 1, 'L'))
                    '|', '.' -> radiateBeam(length, width, queue, energizedTiles, Beam(beam.y - 1, beam.x, 'U'))
                }
            }

            'D' -> {
                when (tile) {
                    '-' -> {
                        radiateBeam(length, width, queue, energizedTiles, Beam(beam.y, beam.x - 1, 'L'))
                        radiateBeam(length, width, queue, energizedTiles, Beam(beam.y, beam.x + 1, 'R'))
                    }
                    '/' -> radiateBeam(length, width, queue, energizedTiles, Beam(beam.y, beam.x - 1, 'L'))
                    '\\' -> radiateBeam(length, width, queue, energizedTiles, Beam(beam.y, beam.x + 1, 'R'))
                    '|', '.' -> radiateBeam(length, width, queue, energizedTiles, Beam(beam.y + 1, beam.x, 'D'))
                }
            }
        }
    }
    val energized = energizedTiles.map { Pair(it.y, it.x) }.toSet().size
    return energized
}

fun radiateBeam(length: Int, width: Int, queue: MutableList<Beam>, energizedTiles: MutableSet<Beam>, b: Beam) {
    if (b.y in 0..<length && b.x in 0..<width && b !in energizedTiles) {
        queue.add(b)
        energizedTiles.add(b)
    }
}