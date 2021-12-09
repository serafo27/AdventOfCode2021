package exercises.day6

import common.Reader

class Part2 {

  private val elementMap = mutableMapOf ("0" to 0L, "1" to 0L, "2" to 0L, "3" to 0L, "4" to 0L, "5" to 0L, "6" to 0L, "7" to 0L, "8" to 0L)

  fun getSolution(): Any {

    val sequence = Reader().readFile("day6/input")
      .first()
      .split(",")
      .toMutableList()

    sequence.forEach {
      elementMap[it] = elementMap[it]!! + 1
    }

    (1 .. 256).forEach { _ ->
      val zeroes = elementMap["0"]!!

      (0 .. 5).forEach { elementMap["$it"] = elementMap["${it+1}"]!! }
      elementMap["6"] = elementMap["7"]!! + zeroes
      elementMap["7"] = elementMap["8"]!!
      elementMap["8"] = zeroes
    }

    return elementMap.values.sum()
  }
}

fun main() {
  println(Part2().getSolution())
}