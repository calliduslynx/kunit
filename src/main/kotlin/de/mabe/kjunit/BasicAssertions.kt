package de.mabe.kjunit

import org.junit.Assert.assertNotEquals
import org.junit.Assert.fail


infix fun <T> T.assert(method: T.() -> Unit): Unit {
  this.apply(method)
}


infix fun <T> T?.mustBe(expected: T?) = BaseFunctions.assert_equals(expected, this)

infix fun <T> T?.mustNotBe(expected: T?) = assertNotEquals(expected, this)

infix fun Any?.mustBeInstanceOf(expected: Class<*>) {
  this mustNotBe null
  val clazz = this!!::class.java
  if (clazz == expected) return

  BaseFunctions.failure("Type of object was expected to be",
      expected.name,
      clazz.name)
}

infix fun Any?.mustBeSameInstanceLike(expected: Any?) {
  if (this === expected) return

  fun Any?.identity() = System.identityHashCode(this).toString()

  BaseFunctions.failure("Variables were expected to reference same object",
      expected!!::class.java.simpleName + "@" + expected.identity(),
      this!!::class.java.simpleName + "@" + this.identity())
}

infix fun <T> T?.mustNotBeSameInstance(expected: T?) = fail("asds")