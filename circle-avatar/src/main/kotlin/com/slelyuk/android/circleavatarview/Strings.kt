package com.slelyuk.android.circleavatarview

import android.graphics.Color.parseColor

private const val COLOR_FORMAT = "#FF%06X"
private const val COUNT_NAME_INITIALS = 2

/**
 * Converts full name into abbreviation with defined count of symbols.
 *
 * @param[initialsCount] count of abbreviation symbols
 * @return abbreviation from given name
 */
fun String.abbreviateName(initialsCount: Int = COUNT_NAME_INITIALS): String {
  return splitToSequence(" ", ".")
      .take(initialsCount)
      .map { w -> w[0].toUpperCase() }
      .fold("", { acc, c -> acc.plus(c) })
}

/**
 * Generates color based on [String.hashCode] via bitwise AND operation.
 *
 * @param[colorFormat] color format string
 * @return color generated from given string
 */
fun String.toColor(colorFormat: String = COLOR_FORMAT): Int {
  return parseColor(String.format(colorFormat, 0xFFFFFF and hashCode()))
}