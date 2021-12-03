package exercises.day3

import common.Reader

data class Rate(val gamma: Int, val epsilon: Int)

class Part1 {

  fun getResult(): Any {

    val matrix = Reader().readFile("day3/input")
      .map { it.toCharArray().toTypedArray() }
      .map { it.map { c -> c.digitToInt() } }

    val binaryGamma = getMatrixTranspose(matrix)
      .map { it.groupingBy { line -> line }.eachCount().maxByOrNull { g -> g.value }!!.key }

    val binaryEpsilon = binaryGamma.map { it xor 1 }
    val rate = Rate(toInt(binaryGamma), toInt(binaryEpsilon))
    return rate.gamma * rate.epsilon
  }

  private fun toInt(binary: List<Int>): Int
    = Integer.parseInt(binary.joinToString(""), 2)

  private fun getMatrixTranspose(matrix: List<List<Int>>): List<List<Int>> {
    val row = matrix.size
    val column = matrix[0].size

    val transpose = Array(column) { IntArray(row) }
    for (i in 0 until row) {
      for (j in 0 until column) {
        transpose[j][i] = matrix[i][j]
      }
    }

    return transpose.map { it.toTypedArray().toList() }
  }
}