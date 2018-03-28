package de.mabe.kjunit

import java.util.*

object present

infix fun Optional<*>.mustBe(p: present) {
  if (this.isPresent) return

  BaseFunctions.failure("Optional was expected", "to be", "present", "but was", "not present")
}

infix fun Optional<*>.mustNotBe(p: present) {
  if (!this.isPresent) return

  BaseFunctions.failure("Optional was expected", "to be", "not present", "but was", "present")
} 