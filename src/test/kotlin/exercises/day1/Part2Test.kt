package exercises.day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Part2Test {

  @Test
  internal fun solution() {
    val actual = Part2().getIncrements()
    assertEquals(1739, actual)
  }
}