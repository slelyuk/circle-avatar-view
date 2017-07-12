package com.slelyuk.android.circleavatarview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.Config
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style.FILL
import android.graphics.PorterDuff.Mode.SRC_IN
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.slelyuk.android.circleavatarview.R.color
import com.slelyuk.android.circleavatarview.R.dimen
import com.slelyuk.android.circleavatarview.R.styleable

/**
 * Circle image view that could use defined name's abbreviation as a placeholder.
 */
class CircleAvatarView : AppCompatImageView {
  internal var circleRadius = 0f
  internal var circleCenterXValue = 0f
  internal var circleCenterYValue = 0f

  private var borderColor = 0
  private var borderWidth = 0

  private var viewSize = 0

  private val borderPaint = Paint()
  private val mainPaint = Paint()

  private var circleDrawable: Drawable? = null
  private val circleRect = Rect()

  constructor(context: Context) : super(context) {
    init(context, null)
  }

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    init(context, attrs)
  }

  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr) {
    init(context, attrs)
  }

  private fun init(context: Context, attrs: AttributeSet?) {
    val defaultBorderColor = ContextCompat.getColor(context, color.default_border_color)
    val defaultBorderWidth = context.resources.getDimensionPixelSize(dimen.default_border_width)

    val arr = obtainStyledAttributes(attrs, styleable.CircleAvatarView)
    try {
      borderColor = arr.getColor(styleable.CircleAvatarView_border_color, defaultBorderColor)
      borderWidth = arr.getDimensionPixelSize(styleable.CircleAvatarView_border_width,
          defaultBorderWidth)
    } finally {
      arr.recycle()
    }

    borderPaint.let {
      it.isAntiAlias = true
      it.style = FILL
      it.color = borderColor
    }

    mainPaint.let {
      it.isAntiAlias = true
      it.xfermode = PorterDuffXfermode(SRC_IN)
    }
  }

  public override fun onDraw(canvas: Canvas) {
    updateViewParams(canvas)

    if (viewSize == 0) return

    val bitmap = cutIntoCircle(drawableToBitmap(circleDrawable)) ?: return
    val radius = circleRadius + borderWidth

    //Draw Border
    canvas.let {
      it.translate(circleCenterXValue, circleCenterYValue)
      it.drawCircle(radius, radius, radius, borderPaint)
      it.drawBitmap(bitmap, 0f, 0f, null)
    }
  }

  private fun drawableToBitmap(drawable: Drawable?): Bitmap? {
    val bitmap = Bitmap.createBitmap(viewSize, viewSize, Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable?.let {
      it.setBounds(0, 0, viewSize, viewSize)
      it.draw(canvas)
    }
    return bitmap
  }

  private fun cutIntoCircle(bitmap: Bitmap?): Bitmap? {
    val output = Bitmap.createBitmap(viewSize, viewSize, Config.ARGB_8888)
    val canvas = Canvas(output)

    canvas.let {
      val radiusWithBorder = (circleRadius + borderWidth)
      it.drawARGB(0, 0, 0, 0)
      it.drawCircle(radiusWithBorder, radiusWithBorder, circleRadius, borderPaint)
      it.drawBitmap(bitmap, circleRect, circleRect, mainPaint)
    }

    return output
  }

  private fun updateViewParams(canvas: Canvas) {
    val viewHeight = canvas.height
    val viewWidth = canvas.width

    viewSize = Math.min(viewWidth, viewHeight)

    circleCenterXValue = (viewWidth - viewSize) / 2f
    circleCenterYValue = (viewHeight - viewSize) / 2f
    circleRadius = (viewSize - borderWidth * 2) / 2f

    circleRect.set(Rect(0, 0, viewSize, viewSize))

    borderWidth = Math.min(viewSize / 3, borderWidth)

    if (viewSize != 0) {
      circleDrawable = this.drawable
    }
  }

  override fun drawableStateChanged() {
    super.drawableStateChanged()
    invalidate()
  }
}