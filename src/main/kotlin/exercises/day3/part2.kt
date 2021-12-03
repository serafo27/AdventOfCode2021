package exercises.day3

import common.Reader

enum class MaxOccurrence(val value: Int) {
  ZERO(0), ONE(1), NONE(-1)
}
data class Occurrences(val zeroes: Int, val ones: Int)

class Part2 {

  fun getResult(): Any {

    var matrix = Reader().readFile("day3/input")
      .map { it.toCharArray().toTypedArray() }
      .map { it.map { c -> c.digitToInt() } }

    var i = 0
    while (matrix.size > 1) {

      val column = getColumn(matrix, i)
      var max = getMaxOccurrence(occurrences(column))

      if(max == MaxOccurrence.NONE)
        max = MaxOccurrence.ONE

      matrix = matrix
        .filter { row -> row[i] == max.value }

      i++
    }

    val oxygen = toInt(matrix[0])

    matrix = Reader().readFile("day3/input")
      .map { it.toCharArray().toTypedArray() }
      .map { it.map { c -> c.digitToInt() } }

    i = 0
    while (matrix.size > 1) {

      val column = getColumn(matrix, i)
      var max = getMinOccurrence(occurrences(column))

      if(max == MaxOccurrence.NONE)
        max = MaxOccurrence.ZERO

      matrix = matrix
        .filter { row -> row[i] == max.value }

      i++
    }

    val co2 = toInt(matrix[0])

    return oxygen * co2
  }

  private fun getColumn(matrix: List<List<Int>>, index: Int): List<Int>
    = getTransposeMatrix(matrix)[index]

  private fun occurrences(vector: List<Int>): Occurrences
    = vector.groupingBy { line -> line }
            .eachCount()
            .let { Occurrences(it[0]!!, it[1]!!) }

  private fun getMaxOccurrence(occurrences: Occurrences): MaxOccurrence
    = when {
        occurrences.zeroes > occurrences.ones -> MaxOccurrence.ZERO
        occurrences.ones > occurrences.zeroes -> MaxOccurrence.ONE
        else -> MaxOccurrence.NONE
      }

  private fun getMinOccurrence(occurrences: Occurrences): MaxOccurrence
      = when {
    occurrences.zeroes < occurrences.ones -> MaxOccurrence.ZERO
    occurrences.ones < occurrences.zeroes -> MaxOccurrence.ONE
    else -> MaxOccurrence.NONE
  }

  private fun toInt(binary: List<Int>): Int
      = Integer.parseInt(binary.joinToString(""), 2)

  private fun getTransposeMatrix(matrix: List<List<Int>>): List<List<Int>> {
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

fun main() {
  println(Part2().getResult())
}