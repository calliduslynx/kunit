@file:Suppress("ConstantConditionIf")

package com.calliduslynx.kjunit

import org.junit.Assert.assertEquals
import org.junit.Assert.fail

abstract class KUnitTest {
  private val letItFail = false // set it to true to see how it looks when tests fail

  protected fun expectAssertionError(method: () -> Unit, expectedMessage: String) {
    try {
      method.invoke()
      fail("Exception expected")
    } catch (ae: AssertionError) {
      if (letItFail) throw ae
      assertEquals(expectedMessage, ae.message)
    }
  }
}