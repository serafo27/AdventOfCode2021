package exercises.day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Part1Test {

  @Test
  internal fun solution() {
    val actual = Part1().getIncrements()
    assertEquals(1715, actual)
  }
}