package exercises.day2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Part2Test {

  @Test
  internal fun solution() {
    val result = Part2().getResult()
    assertEquals(1947878632, result)
  }
}