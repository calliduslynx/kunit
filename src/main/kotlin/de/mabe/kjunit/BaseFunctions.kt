package de.mabe.kjunit

internal object BaseFunctions {
  private fun Any?.toStringWithType(): String = if (this == null) "null" else (this::class.java.name + "<" + this + ">")


  fun <A, B> assert_equals(expected: A?, actual: B?) {
    if (expected == actual) return

    if (expected.toString() != actual.toString()) {
      failure("Following values were expected to be equal", expected.toString(), actual.toString())
    } else
      failure("Following values were expected to be equal", expected.toStringWithType(), actual.toStringWithType())
  }

  fun failure(message: String, expected: String, actual: String) {
    val body = """
      |
      |------------------------------------------------------------
      |$message:
      |  expected : $expected
      |    actual : $actual
      |------------------------------------------------------------
      |(for IntelliJ)
      |expected: $expected but was: $actual
      """.trimMargin()


    throw AssertionError(body)
  }
}