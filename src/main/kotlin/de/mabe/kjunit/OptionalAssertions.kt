package de.mabe.kjunit

import org.junit.Assert
import java.util.*

object present

infix fun Optional<*>.mustBe(p: present) = Assert.assertTrue("Optional is not present", this.isPresent)

infix fun Optional<*>.mustNotBe(p: present) = Assert.assertTrue("Optional is present", !this.isPresent)