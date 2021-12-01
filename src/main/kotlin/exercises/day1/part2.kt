package exercises.day1

import common.Reader

fun main() {
  val reader = Reader()

  val lines = reader.readFile("day1/input")

  val measurements: MutableList<Int> = mutableListOf()
  for (n in 0..lines.size-3)
    measurements.add(lines[n].toInt() + lines[n+1].toInt() + lines[n+2].toInt())

  var increments = 0
  for (n in 0..measurements.size-2) {
    val currentLine = measurements[n]
    val nextLine = measurements[n+1]

    if(Trend.compare(currentLine, nextLine) == Trend.INCREASE)
      increments++
  }

  println(increments)
}