package exercises.day10

import common.Reader

data class ExpandedChunk(val line: String, val state: State, val parentheses: String? = null)

class Part2 {

  fun getSolution(): Any {

    val input = Reader().readFile("day10/input")
    val chunks = input.map { line ->
      val chars = line.split("")

      val parentheses = mutableListOf<String>()
      var charIndex = 0
      while (charIndex < chars.size) {
        val currentChar = chars[charIndex]

        when (currentChar) {
          "(", "[", "{", "<" -> parentheses.add(currentChar)
          ")", "]", "}", ">" -> {
            val last = parentheses.removeLast()
            if(last != getOppositeParentheses(currentChar))
              return@map ExpandedChunk(line, State.CORRUPTED)
          }
        }

        charIndex += 1
      }

      if(parentheses.isEmpty())
        return@map ExpandedChunk(line, State.GOOD)
      return@map ExpandedChunk(line, State.INCOMPLETE, parentheses.joinToString(""))
    }

    val sortedScores = chunks
      .filter { it.state == State.INCOMPLETE }
      .map { it.parentheses!!.reversed().map { c -> getOppositeParentheses(c.toString()) }.joinToString("") }
      .map { it.fold(0L) { acc, c -> (acc * 5L) + getErrorValue(c.toString()) } }
      .sorted()

    return sortedScores[sortedScores.size/2]
  }

  private fun getOppositeParentheses(char: String)
    = when(char) {
      ")" -> "("
      "]" -> "["
      "}" -> "{"
      ">" -> "<"
      "(" -> ")"
      "[" -> "]"
      "{" -> "}"
      "<" -> ">"
      else -> throw RuntimeException("Not valid char")
    }


  private fun getErrorValue(char: String)
    = when(char) {
      ")" -> 1L
      "]" -> 2L
      "}" -> 3L
      ">" -> 4L
      else -> throw RuntimeException("Not valid char")
    }

}

fun main() {
  println(Part2().getSolution())
}