package exercises.day8

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Part1Test {

  @Test
  internal fun solution() {
    val solution = Part1().getSolution()
    assertEquals(409, solution)
  }
}