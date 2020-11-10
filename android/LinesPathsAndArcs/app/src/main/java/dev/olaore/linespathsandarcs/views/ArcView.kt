package dev.olaore.linespathsandarcs.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ArcView @JvmOverloads
    constructor(
        private var ctx: Context, private var attributeSet: AttributeSet? = null, private var defStyleAttr: Int = 0
    ): View(ctx, attributeSet, defStyleAttr) {

    private var arcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth = 3f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawArc(canvas)
    }

    private fun drawArc(canvas: Canvas?) {
        canvas?.drawArc(
            100f, 100f, width - 100f, height / 2f, -90f, 270f, false, arcPaint
        )
    }

}