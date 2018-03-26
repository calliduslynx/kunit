package de.mabe.kjunit

import org.junit.Test
import java.util.*

class OptionalAssertionsTest : KUnitTest() {
  @Test fun `Optional mustBe present - positiv`() {
    val opt = Optional.of("XX")
    opt mustBe present
  }

  @Test fun `Optional mustBe present - negativ`() {
    val opt = Optional.empty<String>()
    expectAssertionError(
        { opt mustBe present },
        "Optional is not present"
    )
  }

  @Test fun `Optional mustNotBe present - positiv`() {
    val opt = Optional.empty<String>()
    opt mustNotBe present
  }

  @Test fun `Optional mustNotBe present - negativ`() {
    val opt = Optional.of("Hallo")
    expectAssertionError(
        { opt mustNotBe present },
        "Optional is present"
    )
  }
}