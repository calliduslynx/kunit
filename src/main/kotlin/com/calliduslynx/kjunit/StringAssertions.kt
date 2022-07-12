@file:Suppress("UNUSED_PARAMETER")

package com.calliduslynx.kjunit


infix fun String?.mustBe(empty: empty) {
  if (this == "") return

  BaseFunctions.failure("Value was expected", "to be", "(empty)", "but was", this.toString(), expectedIntelliJ = "")
}

infix fun String?.mustContain(expectedPart: String) {
  if (this != null && this.contains(expectedPart)) return

  BaseFunctions.failure("Value was expected", "to contain", expectedPart, "but was", this.toString(), expectedIntelliJ = "..$expectedPart..")
}

infix fun String?.mustNotContain(expectedPart: String) {
  if (this != null && !this.contains(expectedPart)) return

  BaseFunctions.failure("Value was expected", "not to contain", expectedPart, "but was", this.toString(), intelliJ = false)
}

infix fun String?.mustStartWith(expectedPart: String) {
  if (this != null && this.startsWith(expectedPart)) return

  BaseFunctions.failure("Value was expected", "to start with", expectedPart, "but was", this.toString(), expectedIntelliJ = "$expectedPart..")
}

infix fun String?.mustEndWith(expectedPart: String) {
  if (this != null && this.endsWith(expectedPart)) return

  BaseFunctions.failure("Value was expected", "to end with", expectedPart, "but was", this.toString(), expectedIntelliJ = "..$expectedPart")
}

infix fun String?.mustMatchRegex(regex: String) = this.mustMatchRegex(Regex(regex))
infix fun String?.mustMatchRegex(regex: Regex) {
  if (this != null && regex.matches(this)) return

  BaseFunctions.failure("Value was expected", "to match regex", regex.pattern, "but was", this.toString(), intelliJ = false)
}


infix fun String?.mustContain(expectedParts: List<String>) {
  val notFound = expectedParts.filterNot { this != null && this.contains(it) }
  if (notFound.isEmpty()) return

  BaseFunctions.failure("Value was expected", "to contain", expectedParts.toString(), "but was", this.toString(), intelliJ = false,
      additionalMessage = "which does not contain", additionalValue = notFound.toString()
  )
}

