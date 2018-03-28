package de.mabe.kjunit

import de.mabe.kjunit.BaseFunctions.toStringWithType


infix fun <T> T.assert(method: T.() -> Unit): Unit {
  this.apply(method)
}


infix fun <T> T?.mustBe(expected: T?) {
  if (this == expected) return

  if (this.toString() != expected.toString()) {
    BaseFunctions.failure("Value was expected", "to be", expected.toString(), "but was", this.toString())
  } else
    BaseFunctions.failure("Value was expected", "to be", expected.toStringWithType(), "but was", this.toStringWithType())
}

infix fun <T> T?.mustNotBe(expected: T?) {
  if (this != expected) return

  BaseFunctions.failure("Value was expected", "not to be", expected.toString(), "but was also", this.toString(), false)
}

infix fun Any?.mustBeOfType(expected: Class<*>) {
  this mustNotBe null
  val clazz = this!!::class.java
  if (clazz == expected) return

  BaseFunctions.failure("Object was expected",
      "to be of type", expected.name,
      "but was of type", clazz.name
  )
}

infix fun Any?.mustBeOfTypeOrSubtype(expected: Class<*>) {
  this mustNotBe null
  val clazz = this!!::class.java
  if (clazz == expected) return
  if (expected.isAssignableFrom(this::class.java)) return

  BaseFunctions.failure("Object was expected", "to be of type or subtype", expected.name, "but was of type", clazz.name)
}

infix fun Any?.mustBeSameInstanceLike(expected: Any?) {
  if (this === expected) return

  fun Any?.identity() = System.identityHashCode(this).toString()

  BaseFunctions.failure("Object was expected",
      "to reference", expected!!::class.java.simpleName + "@" + expected.identity(),
      "but referenced", this!!::class.java.simpleName + "@" + this.identity())
}

infix fun Any?.mustNotBeSameInstanceLike(expected: Any?) {
  if (this !== expected) return

  fun Any?.identity() = System.identityHashCode(this).toString()

  BaseFunctions.failure("Object was expected",
      "not to reference", expected!!::class.java.simpleName + "@" + expected.identity(),
      "but also referenced", this!!::class.java.simpleName + "@" + this.identity(),
      false)
}