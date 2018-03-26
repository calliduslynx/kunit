package de.mabe.kjunit

import org.junit.Assert

object empty

infix fun String?.mustBe(empty: empty) = this mustBe ""

infix fun String?.mustContain(expectedPart: String) = Assert.assertTrue("'$this' doesn't contain '$expectedPart'", this != null && this.contains(expectedPart))

infix fun String?.mustContain(expectedParts: List<String>) = expectedParts.forEach { this mustContain it }

infix fun String?.mustNotContain(expectedPart: String) = Assert.assertTrue("'$this' contains '$expectedPart'", this != null && !this.contains(expectedPart))

infix fun String?.mustStartWith(expectedPart: String) = Assert.assertTrue("'$this' doesn't start with '$expectedPart'", this != null && this.startsWith(expectedPart))

infix fun String?.mustEndWith(expectedPart: String) = Assert.assertTrue("'$this' doesn't end with '$expectedPart'", this != null && this.endsWith(expectedPart))

infix fun String?.mustMatchRegex(regex: String) = Assert.assertTrue("'$this' doesn't match Regex '$regex'", this != null && Regex(regex).matches(this))
