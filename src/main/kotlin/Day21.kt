import java.io.File

data class Plot(val y: Int, val x: Int)

fun main(args: Array<String>) {
    val garden = File("src/main/kotlin/input21.txt").readLines()
        .map { line -> line.toList() }

    var startY = 0
    var startX = 0
    for (y in garden.indices) {
        for (x in garden[0].indices) {
            if (garden[y][x] == 'S') {
                startY = y
                startX = x
            }
        }
    }

    var latest = mutableListOf<Plot>()
    latest.add(Plot(startY, startX))

    val steps = 64
    repeat(steps) {
        val temp = mutableListOf<Plot>()
        while (latest.isNotEmpty()) {
            val plot = latest.removeAt(0)
            val plotN = Plot(plot.y - 1, plot.x)
            if (plotN.y >= 0 && garden[plotN.y][plotN.x] != '#' && plotN !in latest && plotN !in temp) {
                temp.add(plotN)
            }
            val plotS = Plot(plot.y + 1, plot.x)
            if (plotS.y <= garden.lastIndex && garden[plotS.y][plotS.x] != '#' && plotS !in latest && plotS !in temp) {
                temp.add(plotS)
            }
            val plotE = Plot(plot.y, plot.x - 1)
            if (plotE.x >= 0 && garden[plotE.y][plotE.x] != '#' && plotE !in latest && plotE !in temp) {
                temp.add(plotE)
            }
            val plotW = Plot(plot.y, plot.x + 1)
            if (plotW.x <= garden[0].lastIndex && garden[plotW.y][plotW.x] != '#' && plotW !in latest && plotW !in temp) {
                temp.add(plotW)
            }
        }
        latest = temp
    }

    println(latest.size)
    // Solution 3737
}