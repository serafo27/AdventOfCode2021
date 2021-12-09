package exercises.day6

import common.Reader

data class Element(val counter: Int)

class Part1 {

  fun getSolution(): Int {

    var sequence = Reader().readFile("day6/input")
      .first()
      .split(",")
      .map { Element(it.toInt()) }
      .toMutableList()

    (1 .. 80).forEach { _ ->
      val decreasedElements = sequence.map {
        Element(it.counter -1)
      }

      val partition = decreasedElements.partition { it.counter == -1 }

      sequence = (partition.second + getNewElements(partition.first.size)).toMutableList()
    }

    return sequence.size
  }

  private fun getNewElements(howMany: Int): List<Element>
    = (1..howMany).map { Element(6) } + (1..howMany).map { Element(8) }

}

fun main() {
  println(Part1().getSolution())
}