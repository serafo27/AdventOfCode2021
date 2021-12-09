package exercises.day7

import common.Reader
import kotlin.math.abs

class Part2 {

  fun getSolution(): Any {
    val positions = Reader().readFile("day7/input")
      .first()
      .split(",")
      .map { it.toInt() }

    val max = positions.maxOrNull()!!
    return (1 .. max).minOf { currentPosition ->
      positions.sumOf { triangularNumber(abs(it - currentPosition)) }
    }
  }

  private fun triangularNumber(n: Int): Int
      = (n*(n+1))/2
}

fun main() {
  println(Part2().getSolution())
}