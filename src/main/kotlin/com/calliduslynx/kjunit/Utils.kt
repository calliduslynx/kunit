package com.calliduslynx.kjunit

import java.io.PrintWriter
import java.io.StringWriter


fun Throwable.getStackTraceAsString(): String {
  val sw = StringWriter()
  val pw = PrintWriter(sw, true)
  this.printStackTrace(pw)
  return sw.buffer.toString()
}