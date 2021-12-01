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

class Part1 {
  fun getIncrements()
    = Reader().readFile("day1/input")
      .zipWithNext { a, b -> Trend.compare(a.toInt(), b.toInt()) }  // execute the compare between items two by two
      .count { it == Trend.INCREASE }
}