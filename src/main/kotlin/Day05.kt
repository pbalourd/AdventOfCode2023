import java.io.File

fun main(args: Array<String>) {
    val seeds = File("src/main/kotlin/input05.txt")
        .readLines()[0]
        .substringAfter("seeds: ")
        .split(" ").map { it.toLong() }


    val maps = File("src/main/kotlin/input05.txt")
        .readText()
        .split("\\R{2}".toRegex())
        .drop(1)
        .map { str ->
            str.lines()
                .drop(1)
                .map {
                    val nums = it.split(" ").map { n -> n.toLong() }
                    Pair(nums[1]..< nums[1] + nums[2], nums[0])
                }
        }

    var minLocation = Long.MAX_VALUE
    for (s in seeds) {
        var value = s
        for (map in maps) {
            for (r in map) {
                if (value in r.first) {
                    value = r.second + value - r.first.first
                    break
                }
            }
        }
        if (value < minLocation) minLocation = value
    }

    println(minLocation)
    // Solution 510109797
}