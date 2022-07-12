@file:Suppress("LocalVariableName")

package com.calliduslynx.kjunit

import kotlin.math.max

internal object BaseFunctions {
  fun Any?.toStringWithType(): String = if (this == null) "null" else (this::class.java.name + "<" + this + ">")

  fun failure(
    message: String,
    expectedMessage: String,
    expected: String,
    actualMessage: String,
    actual: String,
    intelliJ: Boolean = true,
    actualIntelliJ: String = actual,
    expectedIntelliJ: String = expected,
    additionalMessage: String? = null,
    additionalValue: String? = null

  ) {
    val maxMessageLength = max(max(expectedMessage.length, actualMessage.length), additionalMessage?.length ?: 0)
    val _expectedMessage = expectedMessage + " ".repeat(maxMessageLength - expectedMessage.length)
    val _actualMessage = actualMessage + " ".repeat(maxMessageLength - actualMessage.length)
    val _additionalMessage = if (additionalMessage == null) null else additionalMessage + " ".repeat(maxMessageLength - additionalMessage.length)

    val _message = StringBuilder().apply {
      appendLine()
      appendLine("------------------------------------------------------------")
      appendLine(message)
      appendLine("  $_expectedMessage : $expected")
      appendLine("  $_actualMessage : $actual")
      if (_additionalMessage != null)
        appendLine("  $_additionalMessage : $additionalValue")
      append("------------------------------------------------------------")
      if (intelliJ)
        append("\n(for IntelliJ) expected: ${expectedIntelliJ}but was: $actualIntelliJ")
    }.toString()

    throw AssertionError(_message)
  }
}