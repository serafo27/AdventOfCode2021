package exercises.day10

import common.Reader

enum class State {
  GOOD, INCOMPLETE, CORRUPTED
}

data class Chunk(val line: String, val state: State, val firstCorruptedChar: String? = null)

class Part1 {

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
              return@map Chunk(line, State.CORRUPTED, currentChar)
          }
        }

        charIndex += 1
      }

      if(parentheses.isEmpty())
        return@map Chunk(line, State.GOOD)
      return@map Chunk(line, State.INCOMPLETE)
    }

    return chunks
      .filter { it.state == State.CORRUPTED }
      .map { getErrorValue(it.firstCorruptedChar!!) }
      .sum()
  }

  private fun getOppositeParentheses(char: String)
    = when(char) {
      ")" -> "("
      "]" -> "["
      "}" -> "{"
      ">" -> "<"
      else -> throw RuntimeException("Not valid char")
    }


  private fun getErrorValue(char: String)
    = when(char) {
      ")" -> 3
      "]" -> 57
      "}" -> 1197
      ">" -> 25137
      else -> throw RuntimeException("Not valid char")
    }

}

fun main() {
  println(Part1().getSolution())
}