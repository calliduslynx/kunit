package com.calliduslynx.kjunit

import org.junit.Assert

// TODO Exception assertions

infix fun Exception.stackMustContain(expected: String) = this.getStackTraceAsString() mustContain expected


infix fun (() -> Any).mustThrowException(exceptionHandler: (Exception) -> Unit) = try {
  this.invoke()
  Assert.fail("Exception expected")
} catch (e: Exception) {
  exceptionHandler.invoke(e)
}
