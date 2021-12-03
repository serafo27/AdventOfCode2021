package exercises.day2

import common.Reader

data class ResultComplex(val depth: Int = 0, val position: Int = 0, val aim: Int = 0)

class Part2 {

  fun getResult(): Int {
    return Reader().readFile("day2/input")
      .map { it.split(' ') }
      .map { Command(CommandType.valueOf(it[0].uppercase()), it[1].toInt()) }
      .fold(ResultComplex()) { acc, next -> aggregate(acc, next) }
      .let { it.position * it.depth }
  }

  private fun aggregate(result: ResultComplex, command: Command): ResultComplex
    = when (command.type) {
      CommandType.FORWARD -> {
        result
          .copy(position = result.position + command.value)
          .copy(depth = result.depth + (result.aim * command.value))
      }
      CommandType.DOWN -> result.copy(aim = result.aim + command.value)
      CommandType.UP -> result.copy(aim = result.aim - command.value)
    }
}

fun main() {
  println(Part2().getResult())
}