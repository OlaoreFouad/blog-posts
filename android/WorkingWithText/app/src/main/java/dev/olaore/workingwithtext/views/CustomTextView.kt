package dev.olaore.workingwithtext.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomTextView @JvmOverloads constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet? = null,
    private val defStyleAttr: Int = 0,
    private val defStyleRes: Int = 0
) : View(ctx, attributeSet, defStyleAttr, defStyleRes) {

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
            drawBackground(canvas)
            drawLines(cnvs)
        }
    }

    private fun drawBackground(canvas: Canvas) {
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), rectPaint)
    }

    private fun drawLines(canvas: Canvas) {
        val width = width
        val height = height
        val spacing = height / 4f

        val startX = 0f

        (0 .. 4).forEach { num ->
            canvas.drawLine(startX, startX, width.toFloat(), startX, linePaint)

            if (num != 4) {
                canvas.translate(0f, spacing)
            }
        }
    }
}