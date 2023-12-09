import java.io.File

fun main(args: Array<String>) {
    val cardOrder1 = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    val cardOrder2 = listOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J')

    val input = File("src/main/kotlin/input07.txt").readLines()

    val hands = input
        .map { h -> h.split(" ")}
        .map { pair ->
            Triple(pair[0],
                pair[0].toListChars(),
                pair[1].toInt())
        }

    val compareHands1 = Comparator<Triple<String, List<Pair<Char, Int>>, Int>> { t1, t2 ->
        var c = 0
        for (i in 0..4) {
            val i1 = cardOrder1.indexOf(t1.first[i])
            val i2 = cardOrder1.indexOf(t2.first[i])
            c = i2.compareTo(i1)
            if (c != 0) break
        }
        c
    }

    val highCardSorted1 = hands.filter { it.second[0].second == 1 && it.second[1].second == 1 }.sortedWith(compareHands1)
    val onePairSorted1 = hands.filter { it.second[0].second == 2 && it.second[1].second == 1 }.sortedWith(compareHands1)
    val twoPairSorted1 = hands.filter { it.second[0].second == 2 && it.second[1].second == 2 }.sortedWith(compareHands1)
    val threeOfAKindSorted1 = hands.filter { it.second[0].second == 3 && it.second[1].second == 1 }.sortedWith(compareHands1)
    val fullHouseSorted1 = hands.filter { it.second[0].second == 3 && it.second[1].second == 2 }.sortedWith(compareHands1)
    val fourOfAKindSorted1 = hands.filter { it.second[0].second == 4 }.sortedWith(compareHands1)
    val fiveOfAKindSorted1 = hands.filter { it.second[0].second == 5 }.sortedWith(compareHands1)

    val sortedHands1 = highCardSorted1 + onePairSorted1 + twoPairSorted1 + threeOfAKindSorted1 + fullHouseSorted1 +
            fourOfAKindSorted1 + fiveOfAKindSorted1

    var totalWinnings = 0L
    for ((index, t) in sortedHands1.withIndex()) {
        totalWinnings += t.third.toLong() * (index + 1)
    }

    println(totalWinnings)
    // Solution 253954294

    val handsWithJ = input
        .map { h -> h.split(" ")}
        .map { pair ->
            val handGroup = pair[0].toListChars()
            val newHand = if (handGroup[0].first == 'J' && handGroup[0].second == 5) pair[0].replace('J', 'A')
            else if (handGroup[0].first == 'J') pair[0].replace('J', handGroup[1].first)
            else pair[0].replace('J', handGroup[0].first)

            Triple(pair[0],
                newHand.toListChars(),
                pair[1].toInt())
        }

    val compareHands2 = Comparator<Triple<String, List<Pair<Char, Int>>, Int>> { t1, t2 ->
        var c = 0
        for (i in 0..4) {
            val i1 = cardOrder2.indexOf(t1.first[i])
            val i2 = cardOrder2.indexOf(t2.first[i])
            c = i2.compareTo(i1)
            if (c != 0) break
        }
        c
    }

    val highCardSorted2 = handsWithJ.filter { it.second[0].second == 1 && it.second[1].second == 1 }.sortedWith(compareHands2)
    val onePairSorted2 = handsWithJ.filter { it.second[0].second == 2 && it.second[1].second == 1 }.sortedWith(compareHands2)
    val twoPairSorted2 = handsWithJ.filter { it.second[0].second == 2 && it.second[1].second == 2 }.sortedWith(compareHands2)
    val threeOfAKindSorted2 = handsWithJ.filter { it.second[0].second == 3 && it.second[1].second == 1 }.sortedWith(compareHands2)
    val fullHouseSorted2 = handsWithJ.filter { it.second[0].second == 3 && it.second[1].second == 2 }.sortedWith(compareHands2)
    val fourOfAKindSorted2 = handsWithJ.filter { it.second[0].second == 4 }.sortedWith(compareHands2)
    val fiveOfAKindSorted2 = handsWithJ.filter { it.second[0].second == 5 }.sortedWith(compareHands2)

    val sortedHands2 = highCardSorted2 + onePairSorted2 + twoPairSorted2 + threeOfAKindSorted2 + fullHouseSorted2 +
            fourOfAKindSorted2 + fiveOfAKindSorted2


    totalWinnings = 0L
    for ((index, t) in sortedHands2.withIndex()) {
        totalWinnings += t.third.toLong() * (index + 1)
    }

    println(totalWinnings)
    // Solution 254837398
}

fun String.toListChars(): List<Pair<Char, Int>> {
    return this.toList()
        .groupBy { it }
        .mapValues { it.value.count() }
        .toList()
        .sortedByDescending { it.second }
}