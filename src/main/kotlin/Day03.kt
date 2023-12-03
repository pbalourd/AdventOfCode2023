import java.io.File

fun main(args: Array<String>) {
    val lines = File("src/main/kotlin/input03.txt").readLines()
    val endLine = lines[0].length - 1

    val chars = lines.map { it.toCharArray().toList()}

    val numStr = "\\d+".toRegex()

    var sum = 0L
    var allGearRatio = 0L
    val gearToNumber = mutableMapOf<Pair<Int, Int>, Int>()
    for ((y, line) in lines.withIndex()) {
        val numbers = numStr.findAll(line)
        for (n in numbers) {
            val value = n.value
            val start = n.range.first
            val end = n.range.last
            var s = start - 1
            if (s < 0) s = 0
            var e = end + 1
            if (e > endLine) e = endLine

            if (y > 0) {
                for (x in s..e) {
                    if (chars[y - 1][x] !in '0'..'9' && chars[y - 1][x] != '.') {
                        sum+= value.toInt()
                        if (Pair(y - 1, x) in gearToNumber) {
                            allGearRatio += value.toInt() * gearToNumber[Pair(y - 1, x)]!!
                            gearToNumber.remove(Pair(y - 1, x))
                        }
                        else if (chars[y - 1][x] == '*') gearToNumber[Pair(y - 1, x)] = value.toInt()
                        break
                    }
                }
            }
            if (y < endLine) {
                for (x in s..e) {
                    if (chars[y + 1][x] !in '0'..'9' && chars[y + 1][x] != '.') {
                        sum+= value.toInt()
                        if (Pair(y + 1, x) in gearToNumber) {
                            allGearRatio += value.toInt() * gearToNumber[Pair(y + 1, x)]!!
                            gearToNumber.remove(Pair(y + 1, x))
                        }
                        else if (chars[y + 1][x] == '*') gearToNumber[Pair(y + 1, x)] = value.toInt()
                        break
                    }
                }
            }
            if (chars[y][s] !in '0'..'9' && chars[y][s] != '.') {
                sum+= value.toInt()
                if (Pair(y, s) in gearToNumber) {
                    allGearRatio += value.toInt() * gearToNumber[Pair(y, s)]!!
                    gearToNumber.remove(Pair(y, s))
                }
                else if (chars[y][s] == '*') gearToNumber[Pair(y, s)] = value.toInt()
            }
            if (chars[y][e] !in '0'..'9' && chars[y][e] != '.') {
                sum+= value.toInt()
                if (Pair(y, e) in gearToNumber) {
                    allGearRatio += value.toInt() * gearToNumber[Pair(y, e)]!!
                    gearToNumber.remove(Pair(y, e))
                }
                else if (chars[y][e] == '*') gearToNumber[Pair(y, e)] = value.toInt()
            }

        }
    }
    println(sum)
    // Solution 539433

    println(allGearRatio)
    // Solution 75847567

}