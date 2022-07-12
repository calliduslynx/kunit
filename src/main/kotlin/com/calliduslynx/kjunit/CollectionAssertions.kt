package com.calliduslynx.kjunit

import org.junit.Assert


// TODO List Assertions -> sorted, unsorted
// TODO Set Assertions
// TODO Collection Assertions

class ListMatcher<T, E>(
    val extractor: (T) -> E,
    vararg val expected: E
)

infix fun <T> List<T>.mustHaveSize(expectedSize: Int) = Assert.assertEquals("Size of List", expectedSize, this.size)

infix fun <T> List<T>.mustHaveAnyMatch(matchMethod: (T) -> Boolean) = Assert.assertTrue("No Matching entry", this.any(matchMethod))

inline infix fun <reified T> List<T>.mustMatch(expected: List<T>) {
  val x: Array<T> = expected.toTypedArray()
  this.mustMatch(ListMatcher({ it }, *x))
}

infix fun <T, E> List<T>.mustMatch(listMatcher: ListMatcher<T, E>) {
  this mustHaveSize listMatcher.expected.size

  val sb = StringBuilder("List is not correct\n     exp    act\n")
  var anythingWrong = false
  this.forEachIndexed { i, _ ->
    val actual = listMatcher.extractor.invoke(this[i])
    val expected = listMatcher.expected[i]
    val match = actual == expected
    anythingWrong = anythingWrong || !match
    val matchSign = if (match) "==" else "<>"
    sb.append("[$i] $expected $matchSign $actual\n")
  }
  if (anythingWrong) {
    Assert.fail(sb.toString())
  }
}

infix fun <T, E> List<T>.allMustMatch(listMatcher: ListMatcher<T, E>) {
  val sb = StringBuilder("List is not correct\n     exp    act\n")
  var anythingWrong = false
  this.forEachIndexed { i, _ ->
    val actual = listMatcher.extractor.invoke(this[i])
    val expected = listMatcher.expected[0]
    val match = actual == expected
    anythingWrong = anythingWrong || !match
    val matchSign = if (match) "==" else "<>"
    sb.append("[$i] $expected $matchSign $actual\n")
  }
  if (anythingWrong) {
    Assert.fail(sb.toString())
  }
}

infix fun <T> T.mustBeIn(list: List<T>) = Assert.assertTrue("$this is not in ${list}", list.contains(this))

infix fun <T> Collection<T>.mustNotContain(element: T) = Assert.assertFalse("$element should not be in List $this", this.contains(element))
