package exercises.day4

import common.Reader

data class CellNumber(val value: String, var marked: Boolean = false)

data class ExtractedNumbers(val numbers: MutableList<String>)
data class Board(val matrix: List<List<CellNumber>>)

class Part1 {

  fun getSolution(): Int {
    val data = Reader()
                .readFile("day4/input")

    val extractedNumbers = ExtractedNumbers(data.take(1)[0].split(",").toMutableList())
    val boards = data.asSequence()
                      .drop(1)
                      .filter { it.isNotEmpty() }
                      .chunked(5)
                      .map { it.map { l -> l.split("\\s+".toRegex()).filter { x -> x.isNotEmpty() }.map { x -> CellNumber(x) } } }
                      .map { Board(it) }
                      .toList()

    var result = 0

    while (result == 0) {
      val extraction = extractedNumbers.numbers.removeFirst()

      boards.forEach { board ->
        board.matrix.forEach { row ->
          row.forEach { column -> if(column.value == extraction) column.marked = true }
        }
      }

      result = boards.map { board ->
        if(hasBingo(board))
          return@map takeOtherNumberSum(board) * extraction.toInt()
        return@map 0
      }.sum()
    }

    return result
  }

  private fun hasBingo(board: Board): Boolean
    = board.matrix.any { it.all { x -> x.marked } }
      || getMatrixTranspose(board.matrix).any { it.all { x -> x.marked } }

  private fun takeOtherNumberSum(board: Board): Int
    = board.matrix.flatten().filter { !it.marked }.sumOf { it.value.toInt() }

  private fun getMatrixTranspose(matrix: List<List<CellNumber>>): List<List<CellNumber>> {
    val transpose = mutableListOf<MutableList<CellNumber>>()

    for (i in matrix.indices) {
      transpose.add(mutableListOf())
      for (j in matrix[i].indices) {
        transpose[i].add(matrix[j][i])
      }
    }

    return transpose
  }
}