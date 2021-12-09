package exercises.day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Part2Test {

  @Test
  internal fun solution() {
    val solution = Part2().getSolution()
    assertEquals(1595330616005, solution)
  }
}