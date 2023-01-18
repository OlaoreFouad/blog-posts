package dev.olaore.workingwithtext.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import dev.olaore.workingwithtext.toDp

class CustomTextView @JvmOverloads constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet? = null,
    private val defStyleAttr: Int = 0,
    private val defStyleRes: Int = 0
) : View(ctx, attributeSet, defStyleAttr, defStyleRes) {

    private var align = Paint.Align.CENTER
    private var textSize = 24f
    private val textBounds = Rect()
    private val textPaint = Paint().apply {
        textAlign = this@CustomTextView.align
        textSize = toDp(textSize)
        color = Color.BLACK
        isAntiAlias = true
    }

    private val rectPaint = Paint().apply {
        color = Color.DKGRAY
        isAntiAlias = true
        strokeWidth = 1.5f
        style = Paint.Style.STROKE
    }

    private val linePaint = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
        strokeWidth = 1.5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let { cnvs ->
            drawText(cnvs)
            drawBackground(cnvs)
            drawLines(cnvs)
        }
    }

    private fun drawText(canvas: Canvas) {
        val text = "testing testing..."
        textPaint.getTextBounds(text, 0, text.length, textBounds)
        val textHeight = textBounds.height()
        val textWidth = textBounds.width()

        val textY = height / 2
        val textX = when (this.align) {
            Paint.Align.CENTER -> {
                (width / 2)
            }
            Paint.Align.LEFT -> {
                textWidth + 10
            }
            Paint.Align.RIGHT -> {
                width - textWidth
            }
        }

        canvas.drawText(text, textX.toFloat(), textY.toFloat(), textPaint)
    }

    private fun drawBackground(canvas: Canvas) {
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), rectPaint)
    }

    private fun drawLines(canvas: Canvas) {
        val width = width
        val height = height
        val spacing = height / 4f

        val startX = 0f

        (0..4).forEach { num ->
            canvas.drawLine(startX, startX, width.toFloat(), startX, linePaint)

            if (num != 4) {
                canvas.translate(0f, spacing)
            }
        }
    }
}