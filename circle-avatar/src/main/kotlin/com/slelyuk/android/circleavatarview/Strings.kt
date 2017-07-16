package com.slelyuk.android.circleavatarview

private const val COUNT_NAME_INITIALS = 2

/**
 * Converts full name into abbreviation with defined count of symbols.
 *
 * @param[initialsCount] count of abbreviation symbols
 * @return abbreviation from given name
 */
internal fun String.abbreviateName(initialsCount: Int = COUNT_NAME_INITIALS): String {
  return splitToSequence(" ", ".")
      .take(initialsCount)
      .map { w -> w[0].toUpperCase() }
      .fold("", { acc, c -> acc.plus(c) })
}