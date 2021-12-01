package exercises.day1

import common.Reader

enum class Trend {
  INCREASE, DECREASE, NONE;

  companion object {
    fun compare(first: Int, second: Int): Trend = when {
      second > first -> INCREASE
      second < first -> DECREASE
      else -> NONE
    }
  }
}

class Calculator {
  fun getIncrements(): Int {
    val reader = Reader()

    val lines = reader.readFile("day1/input")

    return lines
      .zipWithNext { a, b -> Trend.compare(a.toInt(), b.toInt()) }
      .count { it == Trend.INCREASE }
  }
}