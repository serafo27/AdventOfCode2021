package exercises.day9

import common.Reader

data class Position(val row: Int, val column: Int)
data class Node(val value: Int, val position: Position)
data class Edge(val n1: Node, val n2: Node)

class Part1 {

  fun getSolution(): Int {

    val matrix: List<List<Int>> = Reader().readFile("day9/input")
      .map { it.split("").filter { c -> c.isNotEmpty() }.map { c -> c.toInt() } }
      .toList()

    val nodes = mutableListOf<Node>()
    for (i in matrix.indices)
      for (j in matrix[i].indices)
           nodes.add(Node(matrix[i][j], Position(i, j)))

    return nodes
      .asSequence()
      .map {
        getAdjacentPositions(it)
          .filter { position -> isValidPosition(position, matrix.size, matrix[0].size) }
          .map { position -> Edge(it, Node(matrix[position.row][position.column], position)) }
      }
      .filter { it.all { e -> e.n1.value < e.n2.value } }
      .flatten()
      .map { it.n1 }
      .distinct()
      .map { it.value }
      .sumOf { it + 1 }
  }

  private fun getAdjacentPositions(node: Node)
    = listOf(
        node.position.copy(row = node.position.row - 1),
        node.position.copy(row = node.position.row + 1),
        node.position.copy(column = node.position.column - 1),
        node.position.copy(column = node.position.column + 1)
      )

  private fun isValidPosition(position: Position, matrixRows: Int, matrixColumns: Int): Boolean
    = position.row >= 0 && position.column >= 0 && position.row < matrixRows && position.column < matrixColumns
}

fun main() {
  println(Part1().getSolution())
}