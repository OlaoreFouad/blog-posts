package dev.olaore.linespathsandarcs.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PointView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr) {

    private val pointPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPoint(
            width / 2f,
            height / 2f,
            pointPaint
        )

//        drawPoint(20f, 20f, canvas)
//        drawPoint(width - 20f, 20f, canvas)
//        drawPoint(width / 2f, 20f, canvas)
    }

    private fun drawPoint(x: Float, y: Float, canvas: Canvas?) {
        canvas?.drawPoint(x, y, pointPaint)
    }

}