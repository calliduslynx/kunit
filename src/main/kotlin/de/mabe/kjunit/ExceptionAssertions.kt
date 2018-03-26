package de.mabe.kjunit

import org.junit.Assert

infix fun Exception.stackMustContain(expected: String) = this.getStackTraceAsString() mustContain expected


infix fun (() -> Any).mustThrowException(exceptionHandler: (Exception) -> Unit) = try {
  this.invoke()
  Assert.fail("Exception expected")
} catch (e: Exception) {
  exceptionHandler.invoke(e)
}
