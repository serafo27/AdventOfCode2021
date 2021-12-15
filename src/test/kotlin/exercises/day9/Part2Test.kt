package exercises.day9

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Part2Test {

  @Test
  internal fun solution() {
    val solution = Part2().getSolution()
    assertEquals(931200, solution)
  }
}