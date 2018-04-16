@file:Suppress("ClassName", "UNUSED_PARAMETER")

package com.calliduslynx.kjunit

import java.math.BigDecimal
import java.math.BigInteger
import java.util.concurrent.atomic.AtomicInteger

object even
object odd
object positive
object negative

private fun eval(result: Boolean, expected: Any, actual: Any, expectedMessage: String? = null) {
  if (result) return

  BaseFunctions.failure("Value was expected",
      expectedMessage ?: "to be",
      expected.toString(),
      "but was",
      actual.toString())
}

private fun bigInt(int: Int) = BigInteger.valueOf(int.toLong())
private fun bigDec(int: Int) = BigDecimal.valueOf(int.toLong())
private fun bigInt(double: Double) = BigInteger.valueOf(double.toLong())
private fun bigDec(double: Double) = BigDecimal.valueOf(double)


//@formatter:off


// equal with int
infix fun          Byte.mustHaveValue(expected: Int) = eval(this.toInt() == expected, expected, this, "to have value of")
infix fun         Short.mustHaveValue(expected: Int) = eval(this.toInt() == expected, expected, this, "to have value of")
infix fun         Float.mustHaveValue(expected: Int) = eval(this.toInt() == expected, expected, this, "to have value of")
infix fun        Double.mustHaveValue(expected: Int) = eval(this.toInt() == expected, expected, this, "to have value of")
infix fun          Long.mustHaveValue(expected: Int) = eval(this.toInt() == expected, expected, this, "to have value of")
infix fun    BigInteger.mustHaveValue(expected: Int) = eval(this == bigInt(expected), expected, this, "to have value of")
infix fun    BigDecimal.mustHaveValue(expected: Int) = eval(this == bigDec(expected), expected, this, "to have value of")
infix fun AtomicInteger.mustHaveValue(expected: Int) = eval(this.get() == expected  , expected, this, "to have value of")

// equal with double
infix fun          Byte.mustHaveValue(expected: Double) = eval(this.toDouble() == expected   , expected, this)
infix fun         Short.mustHaveValue(expected: Double) = eval(this.toDouble() == expected   , expected, this)
infix fun         Float.mustHaveValue(expected: Double) = eval(this.toDouble() == expected   , expected, this)
infix fun        Double.mustHaveValue(expected: Double) = eval(this == expected              , expected, this)
infix fun          Long.mustHaveValue(expected: Double) = eval(this.toDouble() == expected   , expected, this)
infix fun    BigInteger.mustHaveValue(expected: Double) = eval(this == bigInt(expected)      , expected, this)
infix fun    BigDecimal.mustHaveValue(expected: Double) = eval(this == bigDec(expected)      , expected, this)
infix fun AtomicInteger.mustHaveValue(expected: Double) = eval(this.get() == expected.toInt(), expected, this)

// even
infix fun          Byte.mustBe(even: even) = eval(this % 2         == 0        , "even", this)
infix fun         Short.mustBe(even: even) = eval(this % 2         == 0        , "even", this)
infix fun           Int.mustBe(even: even) = eval(this % 2         == 0        , "even", this)
infix fun          Long.mustBe(even: even) = eval(this % 2         == 0L       , "even", this)
infix fun    BigInteger.mustBe(even: even) = eval(this % bigInt(2) == bigInt(0), "even", this)
infix fun AtomicInteger.mustBe(even: even) = eval(this.get() % 2   == 0        , "even", this)

// odd
infix fun          Byte.mustBe(odd: odd) = eval(this % 2         != 0        , "odd", this)
infix fun         Short.mustBe(odd: odd) = eval(this % 2         != 0        , "odd", this)
infix fun           Int.mustBe(odd: odd) = eval(this % 2         != 0        , "odd", this)
infix fun          Long.mustBe(odd: odd) = eval(this % 2         != 0L       , "odd", this)
infix fun    BigInteger.mustBe(odd: odd) = eval(this % bigInt(2) != bigInt(0), "odd", this)
infix fun AtomicInteger.mustBe(odd: odd) = eval(this.get() % 2   != 0        , "odd", this)

// positive
infix fun          Byte.mustBe(positive: positive) = eval(this > 0        , "positive", this)
infix fun         Short.mustBe(positive: positive) = eval(this > 0        , "positive", this)
infix fun         Float.mustBe(positive: positive) = eval(this > 0F       , "positive", this)
infix fun        Double.mustBe(positive: positive) = eval(this > 0.0      , "positive", this)
infix fun          Long.mustBe(positive: positive) = eval(this > 0L       , "positive", this)
infix fun    BigInteger.mustBe(positive: positive) = eval(this > bigInt(0), "positive", this)
infix fun    BigDecimal.mustBe(positive: positive) = eval(this > bigDec(0), "positive", this)
infix fun AtomicInteger.mustBe(positive: positive) = eval(this.get() > 0  , "positive", this)

// negative
infix fun          Byte.mustBe(negative: negative) = eval(this < 0        , "negative", this)
infix fun         Short.mustBe(negative: negative) = eval(this < 0        , "negative", this)
infix fun         Float.mustBe(negative: negative) = eval(this < 0F       , "negative", this)
infix fun        Double.mustBe(negative: negative) = eval(this < 0.0      , "negative", this)
infix fun          Long.mustBe(negative: negative) = eval(this < 0L       , "negative", this)
infix fun    BigInteger.mustBe(negative: negative) = eval(this < bigInt(0), "negative", this)
infix fun    BigDecimal.mustBe(negative: negative) = eval(this < bigDec(0), "negative", this)
infix fun AtomicInteger.mustBe(negative: negative) = eval(this.get() < 0  , "negative", this)


infix fun          Byte.mustBeLessThan(negative: negative) = eval(this < 0        , "negative", this)
infix fun          Byte.mustBeEqualOrLessThan(negative: negative) = eval(this < 0        , "negative", this)
infix fun          Byte.mustBeEqualOrGreaterThan(negative: negative) = eval(this < 0        , "negative", this)
infix fun          Byte.mustBeGreaterThan(negative: negative) = eval(this < 0        , "negative", this)



// 
// TODO closeTo für alla
// TODO close without specific value is 0.1%
// TODO closeTo -> Constante


infix fun Double.mustBeCloseTo(expected: Double) = eval(false, "" ,"")
infix fun Double.mustBeCloseTo(expectedAndDelta: Pair<Double, Double>) = eval(false, "" ,"")



