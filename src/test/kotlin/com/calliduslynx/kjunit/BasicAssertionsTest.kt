package com.calliduslynx.kjunit

import org.junit.Test
import java.math.BigInteger

class BasicAssertionsTest : KUnitTest() {
  @Test fun `mustBe - positive`() {
    5 mustBe 5
    "Hallo" mustBe "Hallo"
    BigInteger.valueOf(23) mustBe BigInteger.valueOf(23)
    null mustBe null
    (23 == 12) mustBe false
  }

  @Test fun `mustBe - negative`() {
    expectAssertionError(
        { 5 mustBe 6 },
        """|
           |------------------------------------------------------------
           |Value was expected
           |  to be   : 6
           |  but was : 5
           |------------------------------------------------------------
           |(for IntelliJ) expected: 6but was: 5
           """.trimMargin())

    expectAssertionError(
        { "Hallo" mustBe "Ballo" },
        """|
           |------------------------------------------------------------
           |Value was expected
           |  to be   : Ballo
           |  but was : Hallo
           |------------------------------------------------------------
           |(for IntelliJ) expected: Ballobut was: Hallo
           """.trimMargin())

    expectAssertionError(
        { 5 mustBe 5L },
        """|
           |------------------------------------------------------------
           |Value was expected
           |  to be   : java.lang.Long<5>
           |  but was : java.lang.Integer<5>
           |------------------------------------------------------------
           |(for IntelliJ) expected: java.lang.Long<5>but was: java.lang.Integer<5>
           """.trimMargin())
  }

  @Test fun `mustNotBe - positive`() {
    // success
    5 mustNotBe
        "Hallo" mustNotBe "Foo"
    BigInteger.valueOf(23) mustNotBe BigInteger.valueOf(22)
    null mustNotBe "Habblo"
    "Hallo" mustNotBe null
    (23 == 12) mustNotBe true
  }

  @Test fun `mustNotBe - negative`() {
    expectAssertionError(
        { 5 mustNotBe 5 },
        """|
           |------------------------------------------------------------
           |Value was expected
           |  not to be    : 5
           |  but was also : 5
           |------------------------------------------------------------
           """.trimMargin())
  }

  @Test fun `mustBeSameInstance - positive`() {
    val x = Integer(2098)
    val y = x

    x mustBeSameInstanceLike y
  }

  @Test fun `mustBeSameInstance - negative`() {
    val x = Integer(2098)
    val y = Integer(2098)

    val hashOfX = System.identityHashCode(x)
    val hashOfY = System.identityHashCode(y)

    expectAssertionError(
        { x mustBeSameInstanceLike y },
        """|
           |------------------------------------------------------------
           |Object was expected
           |  to reference   : Integer@$hashOfY
           |  but referenced : Integer@$hashOfX
           |------------------------------------------------------------
           |(for IntelliJ) expected: Integer@${hashOfY}but was: Integer@$hashOfX
           """.trimMargin())
  }

  @Test fun `mustNotBeSameInstance - positive`() {
    val x = Integer(2098)
    val y = Integer(2098)

    x mustBe y

    x mustNotBeSameInstanceLike y
  }

  @Test fun `mustNotBeSameInstance - negative`() {
    val x = Integer(2098)
    val y = x

    val hashOfX = System.identityHashCode(x)

    expectAssertionError(
        { x mustNotBeSameInstanceLike y },
        """|
           |------------------------------------------------------------
           |Object was expected
           |  not to reference    : Integer@$hashOfX
           |  but also referenced : Integer@$hashOfX
           |------------------------------------------------------------
           """.trimMargin())
  }

  @Test fun `mustBeOfType - positive`() {
    5 mustBeOfType java.lang.Integer::class.java
    "X" mustBeOfType String::class.java
  }

  @Test fun `mustBeOfType - negative`() {
    expectAssertionError(
        { 5 mustBeOfType String::class.java },
        """|
           |------------------------------------------------------------
           |Object was expected
           |  to be of type   : java.lang.String
           |  but was of type : java.lang.Integer
           |------------------------------------------------------------
           |(for IntelliJ) expected: java.lang.Stringbut was: java.lang.Integer
           """.trimMargin())
  }

  @Test fun `mustBeOfTypeOrSubtype - positive`() {
    open class A
    open class B : A()
    open class C : B()

    val a = A()
    val b = B()
    val c = C()

    a mustBeOfTypeOrSubtype A::class.java
    b mustBeOfTypeOrSubtype A::class.java
    c mustBeOfTypeOrSubtype A::class.java
    b mustBeOfTypeOrSubtype B::class.java
    c mustBeOfTypeOrSubtype B::class.java
    c mustBeOfTypeOrSubtype C::class.java
  }

  @Test fun `mustBeOfTypeOrSubtype - negative`() {
    expectAssertionError(
        { 5 mustBeOfTypeOrSubtype String::class.java },
        """|
           |------------------------------------------------------------
           |Object was expected
           |  to be of type or subtype : java.lang.String
           |  but was of type          : java.lang.Integer
           |------------------------------------------------------------
           |(for IntelliJ) expected: java.lang.Stringbut was: java.lang.Integer
           """.trimMargin())
  }


}