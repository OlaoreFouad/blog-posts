package dev.olaore.linespathsandarcs.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class LinesView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr) {

    private val firstLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 5f
    }
    private val secondLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawFirstLine(canvas)
        drawSecondLine(canvas)
    }   

    private fun drawFirstLine(canvas: Canvas?) {
        canvas?.drawLine(50f, 50f, width - 50f, height / 2f, firstLinePaint)
    }

    private fun drawSecondLine(canvas: Canvas?) {
        canvas?.drawLine(50f, height / 2f, width - 50f, 50f, secondLinePaint)
    }

}