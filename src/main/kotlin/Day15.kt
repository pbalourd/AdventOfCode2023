import java.io.File

data class Slot(var label: String, var focalLength: Int)

fun main(args: Array<String>) {
    val sequence = File("src/main/kotlin/input15.txt").readText()
        .split(",")

    val sum = sequence.map(String::hash).sum()

    println(sum)
    // Solution 510792

    val boxes = List<MutableMap<String, Int>>(256) { emptyMap<String, Int>().toMutableMap() }
    for (s in sequence) {
        val label = s.substringBefore("=").substringBefore("-")
        val operation = s[label.length]
        val hashLabel = label.hash()
        val focalLength = if (operation == '=') s.substringAfter("=").toInt() else - 1

        if (operation == '-') boxes[hashLabel].remove(label)
        else boxes[hashLabel][label] = focalLength
    }
    val focusingPower = boxes.mapIndexed { i, m -> m.toList().foldIndexed(0) { j, acc, fl ->
        acc + (i + 1) * (j + 1) * fl.second }
    }.sum()

    println(focusingPower)
    // Solution 269410
}

fun String.hash() = this.fold(0) { acc, c -> ((acc + c.code) * 17) % 256 }