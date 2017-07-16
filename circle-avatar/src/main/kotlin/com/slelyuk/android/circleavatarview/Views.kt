package com.slelyuk.android.circleavatarview

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View

internal const val DEFAULT_TEXT_SIZE_PERCENTAGE = 33

/**
 * Gets styled attributes.
 *
 * @param[attrs] attrs
 * @param[styleable] styleable
 * @return styled attributes
 */
internal fun View.obtainStyledAttributes(attrs: AttributeSet?, styleable: IntArray): TypedArray {
  return context.theme.obtainStyledAttributes(attrs, styleable, 0, 0)
}

