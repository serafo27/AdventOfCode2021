package exercises.day9

import common.Reader

class Part2 {

  fun getSolution(): Any {

    val matrix: List<List<Int>> = Reader().readFile("day9/input")
      .map { it.split("").filter { c -> c.isNotEmpty() }.map { c -> c.toInt() } }
      .toList()

    val nodes = mutableListOf<Node>()
    for (i in matrix.indices)
      for (j in matrix[i].indices)
           nodes.add(Node(matrix[i][j], Position(i, j)))

    val lowPoints = nodes
      .asSequence()
      .map {
        getNeighbours(it, matrix.size, matrix[0].size)
          .map { position -> Edge(it, Node(matrix[position.row][position.column], position)) }
      }
      .filter { it.all { e -> e.n1.value < e.n2.value } }
      .flatten()
      .distinctBy { it.n1 }
      .map { it.n1 }
      .toList()

    val basinsSizes = mutableListOf<Int>()

    lowPoints.forEach { lowPoint ->
      val basin = mutableListOf(lowPoint)
      val queue = mutableListOf(lowPoint)

      while (queue.isNotEmpty()) {
        val currentNode = queue.removeFirst()
        val neighbours = getNeighbours(currentNode, matrix.size, matrix[0].size)

        val basinsFound = neighbours
          .map { Node(matrix[it.row][it.column], it) }
          .filter { !basin.contains(it) }
          .filter { it.value < 9 }

        basin.addAll(basinsFound)
        queue.addAll(basinsFound)
      }

      basinsSizes.add(basin.size)
    }

    return basinsSizes.sortedDescending().take(3).reduce { acc, i ->  acc * i }
  }

  private fun getNeighbours(node: Node, matrixRows: Int, matrixColumns: Int): List<Position>
    = listOf(
        node.position.copy(row = node.position.row - 1),
        node.position.copy(row = node.position.row + 1),
        node.position.copy(column = node.position.column - 1),
        node.position.copy(column = node.position.column + 1)
      ).filter { isValidPosition(it, matrixRows, matrixColumns) }

  private fun isValidPosition(position: Position, matrixRows: Int, matrixColumns: Int): Boolean
    = position.row >= 0 && position.column >= 0 && position.row < matrixRows && position.column < matrixColumns
}

fun main() {
  println(Part2().getSolution())
}