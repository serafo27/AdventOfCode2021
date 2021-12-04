package exercises.day4

import common.Reader

class Part2 {

  fun getSolution(): Int {
    val data = Reader()
                .readFile("day4/input")

    val extractedNumbers = ExtractedNumbers(data.take(1)[0].split(",").toMutableList())
    var boards = data.asSequence()
                      .drop(1)
                      .filter { it.isNotEmpty() }
                      .chunked(5)
                      .map { it.map { l -> l.split("\\s+".toRegex()).filter { x -> x.isNotEmpty() }.map { x -> CellNumber(x) } } }
                      .map { Board(it) }
                      .toList()

    val winners: MutableList<Board> = mutableListOf()

    var extraction: String = "None"
    while (boards.isNotEmpty()) {
      extraction = extractedNumbers.numbers.removeFirst()

      boards.forEach { board ->
        board.matrix.forEach { row ->
          row.forEach { column -> if(column.value == extraction) column.marked = true }
        }
      }

      val partition = boards.partition { hasBingo(it) }
      winners.addAll(partition.first)
      boards = partition.second
    }

    return takeOtherNumberSum(winners.last()) * extraction.toInt()
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