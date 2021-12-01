package exercises.day1

import common.Reader

class Part2 {
  fun getIncrements()
    = Reader().readFile("day1/input")
        .asSequence()
        .map { it.toInt() }  // convert to int
        .windowed(3)  // create sub array of three items
        .map { it.sum() }  // sum sub arrais
        .zipWithNext { a, b -> Trend.compare(a, b) }  // execute the compare between items two by two
        .count { it == Trend.INCREASE }
}