package de.mabe.kjunit

import org.junit.Test
import java.math.BigInteger

class BasicAssertionsTest : KUnitTest() {
  @Test fun `mustBe - positiv`() {
    5 mustBe 5
    "Hallo" mustBe "Hallo"
    BigInteger.valueOf(23) mustBe BigInteger.valueOf(23)
    null mustBe null
    (23 == 12) mustBe false
  }

  @Test fun `mustBe - negativ`() {
    expectAssertionError(
        { 5 mustBe 6 },
        """|
           |------------------------------------------------------------
           |Following values were expected to be equal:
           |  expected : 6
           |    actual : 5
           |------------------------------------------------------------
           |(for IntelliJ)
           |expected: 6 but was: 5
           """.trimMargin())

    expectAssertionError(
        { "Hallo" mustBe "Ballo" },
        """|
           |------------------------------------------------------------
           |Following values were expected to be equal:
           |  expected : Ballo
           |    actual : Hallo
           |------------------------------------------------------------
           |(for IntelliJ)
           |expected: Ballo but was: Hallo
           """.trimMargin())

    expectAssertionError(
        { 5 mustBe 5L },
        """|
           |------------------------------------------------------------
           |Following values were expected to be equal:
           |  expected : java.lang.Long<5>
           |    actual : java.lang.Integer<5>
           |------------------------------------------------------------
           |(for IntelliJ)
           |expected: java.lang.Long<5> but was: java.lang.Integer<5>
           """.trimMargin())
  }

  @Test fun `mustNotBe - positiv`() {
    // success
    5 mustNotBe 6
    "Hallo" mustNotBe "Foo"
    BigInteger.valueOf(23) mustNotBe BigInteger.valueOf(22)
    null mustNotBe "Habblo"
    "Hallo" mustNotBe null
    (23 == 12) mustNotBe true
  }

  @Test fun `mustBeSameInstance - positiv`() {
    val x = Integer(2098)
    val y = x

    x mustBeSameInstanceLike y
  }

  @Test fun `mustBeSameInstance - negativ`() {
    val x = Integer(2098)
    val y = Integer(2098)

    val hashOfX = System.identityHashCode(x)
    val hashOfY = System.identityHashCode(y)

    expectAssertionError(
        { x mustBeSameInstanceLike y },
        """|
           |------------------------------------------------------------
           |Variables were expected to reference same object:
           |  expected : Integer@$hashOfY
           |    actual : Integer@$hashOfX
           |------------------------------------------------------------
           |(for IntelliJ)
           |expected: Integer@$hashOfY but was: Integer@$hashOfX
           """.trimMargin())
  }

  @Test fun `mustBeInstanceOf - positiv`() {
    5 mustBeInstanceOf java.lang.Integer::class.java
    "X" mustBeInstanceOf String::class.java
  }

  @Test fun `mustBeInstanceOf - negativ`() {
    expectAssertionError(
        { 5 mustBeInstanceOf String::class.java },
        """|
           |------------------------------------------------------------
           |Type of object was expected to be:
           |  expected : java.lang.String
           |    actual : java.lang.Integer
           |------------------------------------------------------------
           |(for IntelliJ)
           |expected: java.lang.String but was: java.lang.Integer
           """.trimMargin())
  }


}