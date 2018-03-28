package de.mabe.kjunit

internal object BaseFunctions {
  fun Any?.toStringWithType(): String = if (this == null) "null" else (this::class.java.name + "<" + this + ">")

  fun failure(message: String, expectedMessage: String, expected: String, actualMessage: String, actual: String, intelliJ: Boolean = true) {
    val maxMessageLength = Math.max(expectedMessage.length, actualMessage.length)
    val _expectedMessage = expectedMessage + " ".repeat(maxMessageLength - expectedMessage.length)
    val _actualMessage = actualMessage + " ".repeat(maxMessageLength - actualMessage.length)

    val _message = StringBuilder().apply {
      appendln()
      appendln("------------------------------------------------------------")
      appendln(message)
      appendln("  $_expectedMessage : $expected")
      appendln("  $_actualMessage : $actual")
      append("------------------------------------------------------------")
      if (intelliJ)
        append("\n(for IntelliJ) expected: $expected but was: $actual")
    }.toString()

    throw AssertionError(_message)
  }
}