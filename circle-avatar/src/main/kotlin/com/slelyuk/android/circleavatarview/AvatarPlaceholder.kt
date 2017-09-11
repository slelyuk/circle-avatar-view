package com.slelyuk.android.circleavatarview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.drawable.Drawable

/**
 * Drawable with defined name and typeface to be used with *CircleAvatarView*.
 *
 * @param[name] name string
 * @param[tf] typeface (*Typeface.DEFAULT* used if not specified)
 * @param[palette] palette
 */
class AvatarPlaceholder(name: String, tf: Typeface? = null,
    val palette: List<Int> = DEFAULT_PALETTE) : Drawable() {

  constructor(name: String, tf: Typeface? = null) : this(name, tf, DEFAULT_PALETTE)

  constructor(name: String) : this(name, null)

  private val textPaint = Paint().apply {
    isAntiAlias = true
    color = Color.WHITE
    typeface = tf
  }

  private val backgroundPaint = Paint().apply {
    isAntiAlias = true
    style = Paint.Style.FILL
    color = pickColor(name)
  }

  private val avatarText: String = abbreviateName(name)

  private var placeholderBounds: Rect? = null
  private var textStartXPoint = 0f
  private var textStartYPoint = 0f

  override fun draw(canvas: Canvas) {
    if (placeholderBounds == null) {
      placeholderBounds = Rect(0, 0, canvas.width, canvas.height)
      applyAvatarTextValues()
    }

    canvas.let {
      it.drawOval(RectF(placeholderBounds), backgroundPaint)
      it.drawText(avatarText, textStartXPoint, textStartYPoint, textPaint)
    }
  }

  override fun setAlpha(alpha: Int) {
    textPaint.alpha = alpha
    backgroundPaint.alpha = alpha
  }

  override fun setColorFilter(colorFilter: ColorFilter?) {
    textPaint.colorFilter = colorFilter
    backgroundPaint.colorFilter = colorFilter
  }

  override fun getOpacity(): Int {
    return PixelFormat.TRANSLUCENT
  }

  private fun applyAvatarTextValues() {
    textPaint.textSize = textSize(bounds)
    textStartXPoint = textStartXPoint()
    textStartYPoint = textStartYPoint()
  }

  fun textStartXPoint(bounds: Rect = this.bounds, text: String = avatarText,
      paint: Paint = textPaint): Float {
    val stringWidth = paint.measureText(text)
    return bounds.width() / 2f - stringWidth / 2f
  }

  fun textStartYPoint(bounds: Rect = this.bounds, paint: Paint = textPaint): Float {
    return bounds.height() / 2f - (paint.ascent() + paint.descent()) / 2f
  }

  fun textSize(bounds: Rect = this.bounds,
      textSizePercentage: Int = DEFAULT_TEXT_SIZE_PERCENTAGE): Float {
    return bounds.height() * textSizePercentage / 100f
  }

  fun pickColor(key: Any): Int {
    return palette[Math.abs(key.hashCode()) % palette.size]
  }

  /**
   * Converts full name into abbreviation with defined count of symbols.
   *
   * @param[initialsCount] count of abbreviation symbols
   * @return abbreviation from given name
   */
  fun abbreviateName(input: String, initialsCount: Int = COUNT_NAME_INITIALS): String {
    val seq: Sequence<String> = input.trim().splitToSequence(" ", ".")
    return if (seq.count() < initialsCount) {
      if (input.length >= initialsCount) input.substring(0, initialsCount) else input
    } else {
      seq.take(initialsCount).map { w -> w[0] }.fold("", { acc, c -> acc.plus(c) })
    }.toUpperCase()
  }

  companion object {
    private val COUNT_NAME_INITIALS = 2
    private val DEFAULT_PALETTE: List<Int> = listOf(
        "#42860E",
        "#2C6965",
        "#005C7D",
        "#584D70",
        "#AA2C6C",
        "#C14139",
        "#BF5500",
        "#636970")
        .map { Color.parseColor(it) }
  }
}