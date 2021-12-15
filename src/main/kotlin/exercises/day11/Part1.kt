package exercises.day11

import common.Reader

data class Position(val row: Int, val column: Int)
data class Octopus(var value: Int, val position: Position)

class Part1 {

  fun getSolution(): Any {

    val matrix = Reader().readFile("day11/input")
      .map { it.split("").filter { c -> c.isNotEmpty() } }
      .map { it.map { c -> c.toInt() }.toMutableList() }

    val octopuses = mutableListOf<Octopus>()
    for (i in matrix.indices)
      for (j in matrix[i].indices)
        octopuses.add(Octopus(matrix[i][j], Position(i, j)))

    var flashingCount = 0
    for (i in 1 .. 100) {
      increaseOne(octopuses)
      flash(octopuses, matrix.size, matrix[0].size)
      val flashed = resetToZero(octopuses)

      flashingCount += flashed
    }

    return flashingCount
  }

  private fun increaseOne(octopuses: List<Octopus>) {
    octopuses.forEach { it.value += 1 }
  }

  private fun resetToZero(octopuses: List<Octopus>): Int {
    val flashing = octopuses.filter { it.value > 9 }
    flashing.forEach { it.value = 0 }
    return flashing.size
  }

  private fun flash(octopuses: List<Octopus>, matrixRows: Int, matrixColumns: Int) {
    val queue = octopuses.filter { it.value > 9 }.map { it.position }.toMutableList()
    val checked = mutableListOf<Position>()

    while (queue.isNotEmpty()) {
      val flashingPosition = queue.removeFirst()
      if(octopuses.first { it.position == flashingPosition }.value > 9) {

        checked.add(flashingPosition)

        val neighbours = getNeighbours(flashingPosition, matrixRows, matrixColumns)
        queue.addAll(neighbours.filter { !checked.contains(it) && !queue.contains(it) })

        neighbours.forEach { p ->
          octopuses.first { o -> o.position == p }.value += 1
        }
      }

    }
  }

  private fun getNeighbours(position: Position, matrixRows: Int, matrixColumns: Int): List<Position>
    = listOf(
      position.copy(row = position.row - 1),
      position.copy(row = position.row + 1),
      position.copy(column = position.column - 1),
      position.copy(column = position.column + 1),
      position.copy(row = position.row + 1, column = position.column + 1),
      position.copy(row = position.row - 1 , column = position.column + 1),
      position.copy(row = position.row + 1 , column = position.column - 1),
      position.copy(row = position.row - 1 , column = position.column - 1)
    ).filter { isValidPosition(it, matrixRows, matrixColumns) }

  private fun isValidPosition(position: Position, matrixRows: Int, matrixColumns: Int): Boolean
      = position.row >= 0 && position.column >= 0 && position.row < matrixRows && position.column < matrixColumns

  private fun printMatrix(octopuses: List<Octopus>): String {
    var output = ""

    for (i in 0..4) {
      for (j in 0..4)
        output += octopuses.first { it.position == Position(i, j) }.value
      output += "\n"
    }

    return output
  }
}

fun main() {
  println(Part1().getSolution())
}