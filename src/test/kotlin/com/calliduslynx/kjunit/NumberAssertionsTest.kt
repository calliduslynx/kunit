package com.calliduslynx.kjunit

import org.junit.Test

class NumberAssertionsTest : KUnitTest() {

  @Test fun `mustHaveValue - Byte|int - positive`() {
    5.toByte() mustHaveValue 5
    (-10).toByte() mustHaveValue -10
  }

  @Test fun `mustHaveValue - Byte|int - negativ`() {
    expectAssertionError(
        { 5.toByte() mustHaveValue 6 },
        """|
           |------------------------------------------------------------
           |Value was expected
           |  to have value of : 6
           |  but was          : 5
           |------------------------------------------------------------
           |(for IntelliJ) expected: 6but was: 5
           """.trimMargin())
  }
}