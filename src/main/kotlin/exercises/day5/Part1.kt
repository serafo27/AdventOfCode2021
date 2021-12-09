package exercises.day5

import common.Reader

data class Line(val c1: Point, val c2: Point)
data class Point(val x: Int, val y: Int)

class Part1 {

  fun getSolution(): Any {

    val input = Reader().readFile("day5/input")

    val partition = input
      .map { it.split(" -> ")
        .map { c -> Point(c.split(",")[0].toInt(), c.split(",")[1].toInt()) }
        .chunked(2)
        .map { l -> Line(l[0], l[1]) }.first()
      }
      .filter { it.c1.x == it.c2.x || it.c1.y == it.c2.y }
      .partition { it.c1.x == it.c2.x }

    val expandedX = partition.first.map { expand(it) }
    val expandedY = partition.second.map { expand(it) }

    return (expandedX + expandedY)
      .flatten()
      .groupBy { it }
      .map { it.value.size }
      .count { it > 1 }
  }

  private fun expand(line: Line): List<Point> {
    val y1 = line.c1.y
    val y2 = line.c2.y

    val x1 = line.c1.x
    val x2 = line.c2.x

    return when {
      (x1 == x2) -> ((y2 .. y1) + (y1 .. y2)).map { r -> Point(line.c1.x, r) }
      (y1 == y2) -> ((x2 .. x1) + (x1 .. x2)).map { r -> Point(r, line.c1.y) }
      else ->emptyList()
    }
  }
}

fun main() {
  println(Part1().getSolution())
}