package exercises.day3

import common.Reader

enum class MaxOccurrence(val value: Int) {
  ZERO(0), ONE(1), NONE(-1)
}

enum class Paramter {
  OXYGEN, CO2
}

data class Occurrences(val zeroes: Int, val ones: Int)

class Part2 {

  fun getResult(): Any {
    val oxygen = getParameter(Paramter.OXYGEN)
    val co2 = getParameter(Paramter.CO2)
    return oxygen * co2
  }

  private fun getParameter(parameter: Paramter): Int {
    var matrix = Reader().readFile("day3/input")
      .map { it.toCharArray().toTypedArray() }
      .map { it.map { c -> c.digitToInt() } }

    var i = 0
    while (matrix.size > 1) {
      val column = getColumn(matrix, i)
      var max = getMaxOccurrence(parameter, column)

      if(max == MaxOccurrence.NONE)
        max = getNeutralMax(parameter)

      matrix = matrix.filter { row -> row[i] == max.value }
      i++
    }

    return toInt(matrix[0])
  }

  private fun getMaxOccurrence(parameter: Paramter, column: List<Int>)
    = when (parameter) {
        Paramter.OXYGEN -> getMaxOccurrence(occurrences(column))
        Paramter.CO2 -> getMinOccurrence(occurrences(column))
      }

  private fun getNeutralMax(parameter: Paramter): MaxOccurrence
    = when (parameter) {
        Paramter.OXYGEN -> MaxOccurrence.ONE
        Paramter.CO2 -> MaxOccurrence.ZERO
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