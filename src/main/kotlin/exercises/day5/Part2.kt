package exercises.day5

import common.Reader
import kotlin.math.abs

enum class Direction {
  NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST, NORTH, SOUTH, EAST, WEST
}

data class DirectedLine(val line: Line, val direction: Direction)

class Part2 {

  fun getSolution(): Int {
    return Reader().readFile("day5/input")
      .map {
        it.split(" -> ")
          .map { c -> Point(c.split(",")[0].toInt(), c.split(",")[1].toInt()) }
          .chunked(2)
          .map { l -> Line(l[0], l[1]) }.first()
      }
      .asSequence()
      .map { DirectedLine(it, getDirection(it)) }
      .map { expand(it) }
      .flatten()
      .groupBy { it }
      .map { it.value.size }
      .count { it > 1 }
  }

  private fun expand(directedLine: DirectedLine): List<Point> {
    val line = directedLine.line

    return when (directedLine.direction) {
      Direction.EAST -> (line.p1.x .. line.p2.x).map { r -> Point(r, line.p1.y) }
      Direction.WEST -> (line.p1.x downTo line.p2.x).map { r -> Point(r, line.p1.y) }
      Direction.NORTH -> (line.p1.y downTo  line.p2.y).map { r -> Point(line.p1.x, r) }
      Direction.SOUTH -> (line.p1.y .. line.p2.y).map { r -> Point(line.p1.x, r) }
      Direction.NORTH_WEST -> (0 .. getDiagDistance(line)).map { Point(line.p1.x - it, line.p1.y - it) }
      Direction.NORTH_EAST -> (0 .. getDiagDistance(line)).map { Point(line.p1.x + it , line.p1.y - it) }
      Direction.SOUTH_WEST -> (0 .. getDiagDistance(line)).map { Point(line.p1.x - it, line.p1.y + it) }
      Direction.SOUTH_EAST -> (0 .. getDiagDistance(line)).map { Point(line.p1.x + it, line.p1.y + it) }
    }
  }

  private fun getDiagDistance(line: Line): Int {
    return (abs(line.p1.x - line.p2.x) + abs(line.p1.y - line.p2.y))/2
  }

  private fun getDirection(line: Line): Direction
    = when {
      line.p1.y > line.p2.y && line.p1.x < line.p2.x -> Direction.NORTH_EAST
      line.p1.y > line.p2.y && line.p1.x > line.p2.x -> Direction.NORTH_WEST
      line.p1.y < line.p2.y && line.p1.x < line.p2.x -> Direction.SOUTH_EAST
      line.p1.y < line.p2.y && line.p1.x > line.p2.x -> Direction.SOUTH_WEST
      line.p1.x == line.p2.x && line.p1.y > line.p2.y -> Direction.NORTH
      line.p1.x == line.p2.x && line.p1.y < line.p2.y -> Direction.SOUTH
      line.p1.y == line.p2.y && line.p1.x > line.p2.x -> Direction.WEST
      line.p1.y == line.p2.y && line.p1.x < line.p2.x -> Direction.EAST
      else -> throw RuntimeException("No direction found")
    }

}

fun main() {
  println(Part2().getSolution())
}