package exercises.day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CalculatorTest {

  @Test
  internal fun solution() {

    val actual = Calculator().getIncrements()
    assertEquals(1715, actual)
  }
}