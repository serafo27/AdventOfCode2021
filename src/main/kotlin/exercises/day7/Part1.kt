package exercises.day7

import common.Reader
import kotlin.math.abs

class Part1 {

  fun getSolution(): Int {
    val positions = Reader().readFile("day7/input")
      .first()
      .split(",")
      .map { it.toInt() }

    return positions.minOf { currentPosition ->
      positions.sumOf { abs(it - currentPosition) }
    }
  }

}

fun main() {
  println(Part1().getSolution())
}