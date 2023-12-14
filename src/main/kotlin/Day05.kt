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
                    Pair(nums[1]..< nums[1] + nums[2], nums[0] - nums[1])
                }
        }
    var minLocation = Long.MAX_VALUE
    for (s in seeds) {
        var value = s
        for (map in maps) {
            for (r in map) {
                if (value in r.first) {
                    value += r.second
                    break
                }
            }
        }
        if (value < minLocation) minLocation = value
    }

    println(minLocation)
    // Solution 510109797

    val inputRanges = mutableListOf<LongRange>()
    for (i in 0..seeds.lastIndex step 2) {
        inputRanges.add(seeds[i]..< seeds[i] + seeds[i + 1])
    }

    minLocation = Long.MAX_VALUE
    for (inputRange in inputRanges) {
        val rangesToCheck = mutableListOf<LongRange>()
        rangesToCheck.add(inputRange)
        for (map in maps) {
            val tempChecked = mutableListOf<LongRange>()
            for (m in map) {
                val tempToCheck = mutableListOf<LongRange>()
                val mapRange = m.first
                val mapValue = m.second

                for (t in rangesToCheck) {
                    val res = rangeIntersection(t, mapRange, mapValue)
                    tempToCheck += res.first
                    tempChecked += res.second
                }
                rangesToCheck.clear()
                for (r in tempToCheck) rangesToCheck.add(r)
            }
            for (r in tempChecked) rangesToCheck.add(r)
        }
        rangesToCheck.forEach { if (it.first < minLocation) minLocation = it.first }
    }
    println(minLocation)
    // Solution 9622622
}

fun rangeIntersection(inputRange: LongRange, mapRange: LongRange, mapValue: Long): Pair<List<LongRange>, List<LongRange>> {
    val noChangedRanges = mutableListOf<LongRange>()
    val changedRanges = mutableListOf<LongRange>()

    // inputRange within mapRange
    if (inputRange.first >= mapRange.first && inputRange.last <= mapRange.last) {
        changedRanges.add(inputRange.first + mapValue..inputRange.last + mapValue)
    }
    // inputRange upper subset of mapRange
    else if (inputRange.first >= mapRange.first && inputRange.first <= mapRange.last) {
        changedRanges.add(inputRange.first + mapValue..mapRange.last + mapValue)
        noChangedRanges.add(mapRange.last + 1..inputRange.last)
    }
    // inputRange lower subset of mapRange
    else if (inputRange.last <= mapRange.last && inputRange.last >= mapRange.first) {
        changedRanges.add(mapRange.first + mapValue..inputRange.last + mapValue)
        noChangedRanges.add(inputRange.first..<mapRange.first)
    }
    // mapRange within inputRange
    else if (inputRange.first < mapRange.first && inputRange.last > mapRange.last) {
        changedRanges.add(mapRange.first + mapValue..mapRange.last + mapValue)
        noChangedRanges.add(inputRange.first..<mapRange.first)
        noChangedRanges.add(mapRange.last + 1..inputRange.last)
    }
    // No intersection
    else {
        noChangedRanges.add(inputRange)
    }

    return Pair(noChangedRanges, changedRanges)
}