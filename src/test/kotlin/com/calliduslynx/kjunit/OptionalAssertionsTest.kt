package com.calliduslynx.kjunit

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
        """|
           |------------------------------------------------------------
           |Optional was expected
           |  to be   : present
           |  but was : not present
           |------------------------------------------------------------
           |(for IntelliJ) expected: presentbut was: not present
           """.trimMargin())
  }

  @Test fun `Optional mustNotBe present - positiv`() {
    val opt = Optional.empty<String>()
    opt mustNotBe present
  }

  @Test fun `Optional mustNotBe present - negativ`() {
    val opt = Optional.of("Hallo")
    expectAssertionError(
        { opt mustNotBe present },
        """|
           |------------------------------------------------------------
           |Optional was expected
           |  to be   : not present
           |  but was : present
           |------------------------------------------------------------
           |(for IntelliJ) expected: not presentbut was: present
           """.trimMargin())
  }
}