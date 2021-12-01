package exercises.day1

import common.Reader

class Comparer {
  fun compare(first: Int, second: Int): Trend
      = when {
    second > first -> Trend.INCREASE
    second < first -> Trend.DECREASE
    else -> Trend.NONE
  }
}

enum class Trend {
  INCREASE, DECREASE, NONE
}

fun main() {
  val reader = Reader()
  val comparer = Comparer()

  val lines = reader.readFile("/Users/serafinodangelillo/Workspace/AdventOfCode2021/src/main/resources/day1/input")

  var increments = 0
  for (n in 0..lines.size-2) {
    val currentLine = lines[n].toInt()
    val nextLine = lines[n+1].toInt()

    if(comparer.compare(currentLine, nextLine) == Trend.INCREASE)
      increments++
  }

  println(increments)
}