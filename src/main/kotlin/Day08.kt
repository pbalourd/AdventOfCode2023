import java.io.File
import kotlin.math.max

fun main(args: Array<String>) {
    val input = File("src/main/kotlin/input08.txt").readLines()

    val instructions = input[0]
    val insSize = instructions.length

    val nodes = hashMapOf<String, Pair<String, String>>()

    for (i in 2..input.lastIndex) {
        val node = input[i].split(" = (", ", ", ")")
        nodes[node[0]] = Pair(node[1], node[2])
    }

    val node = "AAA"
    val endNode = listOf("ZZZ")

    val steps1 = stepsToEnd(node, endNode, instructions, nodes, insSize)
    println(steps1)
    //Solution 20221

    val aNodes = nodes.filter { it.key[2] == 'A' }.map { it.key }
    val zNodes = nodes.filter { it.key[2] == 'Z' }.map { it.key }

    val stepsPerANode = mutableListOf<Int>()
    for (a in aNodes) stepsPerANode.add(stepsToEnd(a, zNodes, instructions, nodes, insSize))

    var steps2 = stepsPerANode[0].toLong()
    for (i in 1..stepsPerANode.lastIndex) steps2 = lcm(steps2, stepsPerANode[i].toLong(), 1)
    println(steps2)
    // Solution 14616363770447
}

private fun stepsToEnd(node: String, endNodes: List<String>, instructions: String,
                       nodes: HashMap<String, Pair<String, String>>, insSize: Int): Int {
    var node1 = node
    var steps = 0
    var position = 0
    while (node1 !in endNodes) {
        node1 = if (instructions[position] == 'L') nodes[node1]!!.first
        else nodes[node1]!!.second
        steps++
        position++
        if (position == insSize) position = 0
    }
    return steps
}

fun lcm(n1: Long, n2: Long, lcm: Long): Long {
    var n1 = n1
    var n2 = n2
    if (n1 == 1L && n2 == 1L) {
        return lcm
    }
    var ans = 1L
    for (i in 2..max(n1, n2)) {
        if (n1 % i == 0L || n2 % i == 0L) {
            if (n1 % i == 0L) {
                n1 /= i
            }
            if (n2 % i == 0L) {
                n2 /= i
            }
            ans = lcm(n1, n2, lcm * i)
            break
        }
    }
    return ans
}