package exercises.day5

import common.Reader

data class Line(val p1: Point, val p2: Point)
data class Point(val x: Int, val y: Int)

class Part1 {

  fun getSolution(): Any {

    val input = Reader().readFile("day5/example")

    val partition = input
      .map { it.split(" -> ")
        .map { c -> Point(c.split(",")[0].toInt(), c.split(",")[1].toInt()) }
        .chunked(2)
        .map { l -> Line(l[0], l[1]) }.first()
      }
      .filter { it.p1.x == it.p2.x || it.p1.y == it.p2.y }
      .partition { it.p1.x == it.p2.x }

    val expandedX = partition.first.map { expand(it) }
    val expandedY = partition.second.map { expand(it) }

    return (expandedX + expandedY)
      .flatten()
      .groupBy { it }
      .map { it.value.size }
      .count { it > 1 }
  }

  private fun expand(line: Line): List<Point> {
    val y1 = line.p1.y
    val y2 = line.p2.y

    val x1 = line.p1.x
    val x2 = line.p2.x

    return when {
      (x1 == x2) -> ((y2 .. y1) + (y1 .. y2)).map { r -> Point(line.p1.x, r) }
      (y1 == y2) -> ((x2 .. x1) + (x1 .. x2)).map { r -> Point(r, line.p1.y) }
      else ->emptyList()
    }
  }
}

fun main() {
  println(Part1().getSolution())
}