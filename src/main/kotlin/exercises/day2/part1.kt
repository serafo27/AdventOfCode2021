package exercises.day2

import common.Reader

enum class CommandType { FORWARD, DOWN, UP }
data class Command(val type: CommandType, val value: Int)

data class Result(val depth: Int = 0, val position: Int = 0)

class Part1 {

  fun getResult(): Int {
    return Reader().readFile("day2/input")
      .map { it.split(' ') }
      .map { Command(CommandType.valueOf(it[0].uppercase()), it[1].toInt()) }
      .fold(Result()) { acc, next -> aggregate(acc, next) }
      .let { it.position * it.depth }
  }

  private fun aggregate(result: Result, command: Command): Result
    = when (command.type) {
      CommandType.FORWARD -> result.copy(position = result.position + command.value)
      CommandType.DOWN -> result.copy(depth = result.depth + command.value)
      CommandType.UP -> result.copy(depth = result.depth - command.value)
    }
}