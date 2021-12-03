package exercises.day3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Part2Test {

  @Test
  internal fun solution() {
    val solution = Part2().getResult()
    assertEquals(2795310, solution)
  }

}