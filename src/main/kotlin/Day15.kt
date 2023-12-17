import java.io.File

fun main(args: Array<String>) {
    val sequence = File("src/main/kotlin/input15.txt").readText()
        .split(",")
        .map { it.toList() }

    val sum = sequence.map {
        it.fold(0) { acc, c -> ((acc + c.code) * 17) % 256 }
    }.sum()

    println(sum)
    // Solution 510792
}