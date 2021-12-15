package exercises.day8

import common.Reader

class Part1 {

  fun getSolution(): Int {

    val input = Reader().readFile("day8/input")

    return input
      .map { it.split("|")[1].split(" ") }
      .flatten()
      .filter { it.isNotEmpty() }
      .filter { (it.length in 1..4) || it.length == 7 }
      .size
  }

}

fun main() {
  println(Part1().getSolution())
}