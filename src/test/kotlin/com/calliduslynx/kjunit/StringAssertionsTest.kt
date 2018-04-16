package com.calliduslynx.kjunit

import org.junit.Test

class StringAssertionsTest : KUnitTest() {

  @Test fun `mustBe empty - positive`() {
    val x = ""

    x mustBe empty
  }

  @Test fun `mustBe empty - negative`() {
    val x = "abc"
    expectAssertionError(
        { x mustBe empty },
        """|
           |------------------------------------------------------------
           |Value was expected
           |  to be   : (empty)
           |  but was : abc
           |------------------------------------------------------------
           |(for IntelliJ) expected: but was: abc
           """.trimMargin())
  }

  @Test fun `mustContain - positive`() {
    "abc" mustContain "bc"
    "Car in the dust" mustContain "in the"
  }

  @Test fun `mustContain - negative`() {
    expectAssertionError(
        { "ABC" mustContain "bc" },
        """|
           |------------------------------------------------------------
           |Value was expected
           |  to contain : bc
           |  but was    : ABC
           |------------------------------------------------------------
           |(for IntelliJ) expected: ..bc..but was: ABC
           """.trimMargin())
  }

  @Test fun `mustContain list - positive`() {
    "abcde" mustContain listOf("a", "c", "e")
    "car in the dust" mustContain listOf("car", "in", "the", "dust")
  }

  @Test fun `mustContain list - negative`() {
    expectAssertionError(
        { "abcde" mustContain listOf("a", "f", "e", "g") },
        """|
           |------------------------------------------------------------
           |Value was expected
           |  to contain             : [a, f, e, g]
           |  but was                : abcde
           |  which does not contain : [f, g]
           |------------------------------------------------------------
           """.trimMargin())
  }

  @Test fun `mustNotContain - positive`() {
    "abc" mustNotContain "B"
    "Car in the dust" mustNotContain "in   the"
  }

  @Test fun `mustNotContain - negative`() {
    expectAssertionError(
        { "ABC" mustNotContain "B" },
        """|
           |------------------------------------------------------------
           |Value was expected
           |  not to contain : B
           |  but was        : ABC
           |------------------------------------------------------------
           """.trimMargin())
  }

  @Test fun `mustStartWith - positive`() {
    "abc" mustStartWith "a"
    "Car in the dust" mustStartWith "Car"
  }

  @Test fun `mustStartWith - negative`() {
    expectAssertionError(
        { "ABC" mustStartWith "a" },
        """|
           |------------------------------------------------------------
           |Value was expected
           |  to start with : a
           |  but was       : ABC
           |------------------------------------------------------------
           |(for IntelliJ) expected: a..but was: ABC
           """.trimMargin())
  }

  @Test fun `mustEndWith - positive`() {
    "abc" mustEndWith "c"
    "Car in the dust" mustEndWith "dust"
  }

  @Test fun `mustEndWith - negative`() {
    expectAssertionError(
        { "ABC" mustEndWith "c" },
        """|
           |------------------------------------------------------------
           |Value was expected
           |  to end with : c
           |  but was     : ABC
           |------------------------------------------------------------
           |(for IntelliJ) expected: ..cbut was: ABC
           """.trimMargin())
  }

  @Test fun `mustMatchRegex - positive`() {
    "abc" mustMatchRegex ".b."
    "Car in the dust" mustMatchRegex Regex(".*in the.*")
  }

  @Test fun `mustMatchRegex - negative`() {
    expectAssertionError(
        { "ABC" mustMatchRegex ".b." },
        """|
           |------------------------------------------------------------
           |Value was expected
           |  to match regex : .b.
           |  but was        : ABC
           |------------------------------------------------------------
           """.trimMargin())
  }
}