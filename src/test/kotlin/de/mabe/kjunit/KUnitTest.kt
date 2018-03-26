package de.mabe.kjunit

import org.junit.Assert.assertEquals
import org.junit.Assert.fail

abstract class KUnitTest {
  protected fun expectAssertionError(method: () -> Unit, expectedMessage: String) {
    try {
      method.invoke()
      fail("Exception expected")
    } catch (ae: AssertionError) {
//      println("----------------------")
//      ae.printStackTrace()
      assertEquals(expectedMessage, ae.message)
    }
  }
}