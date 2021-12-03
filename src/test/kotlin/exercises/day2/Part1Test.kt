package exercises.day2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Part1Test {

  @Test
  internal fun solution() {
    val result = Part1().getResult()
    assertEquals(1938402, result)
  }
}