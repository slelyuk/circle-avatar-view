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
import com.slelyuk.android.circleavatarview.AvatarPlaceholder.Companion

/**
 * Drawable with defined name and typeface to be used with *CircleAvatarView*.
 *
 * @param[name] name string
 * @param[tf] typeface (*Typeface.DEFAULT* used if not specified)
 * @param[palette] palette
 */
class AvatarPlaceholder(name: String, tf: Typeface? = null, val palette: List<Int> = AvatarPlaceholder.DEFAULT_PALETTE) : Drawable() {

  private val textPaint = Paint().apply {
    isAntiAlias = true
    color = Color.WHITE
    typeface = tf
  }

  private val backgroundPaint = Paint().apply {
    isAntiAlias = true
    style = Paint.Style.FILL
    color = getColor(name)
  }

  private val avatarText: String = name.abbreviateName()

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
    textPaint.textSize = calculateTextSize()
    textStartXPoint = calculateTextStartXPoint()
    textStartYPoint = calculateTextStartYPoint()
  }

  private fun calculateTextStartXPoint(): Float {
    val stringWidth = textPaint.measureText(avatarText)
    return bounds.width() / 2f - stringWidth / 2f
  }

  private fun calculateTextStartYPoint(): Float {
    return bounds.height() / 2f - (textPaint.ascent() + textPaint.descent()) / 2f
  }

  private fun calculateTextSize(textSizePercentage: Int = DEFAULT_TEXT_SIZE_PERCENTAGE): Float {
    return bounds.height() * textSizePercentage / 100f
  }

  private fun getColor(key: Any): Int {
    return palette[Math.abs(key.hashCode()) % palette.size]
  }

  companion object {
    val DEFAULT_PALETTE: List<Int> = listOf(
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