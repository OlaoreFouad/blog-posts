package dev.olaore.linespathsandarcs.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CenteredArcView @JvmOverloads
    constructor(
        private var ctx: Context, private var attributeSet: AttributeSet? = null, private var defStyleAttr: Int = 0
    ): View(ctx, attributeSet, defStyleAttr) {

    private var arcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.YELLOW
    }

    private var eyePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawArc(canvas)
        drawEye(canvas)
    }

    private fun drawArc(canvas: Canvas?) {
        canvas?.drawArc(
            100f, 100f, width - 100f, height / 2f, 0f, 315f, true, arcPaint
        )
    }

    private fun drawEye(canvas: Canvas?) {
        canvas?.translate(width / 2f, 100f)
        canvas?.drawCircle(
            60f, 110f, 40f, eyePaint
        )
    }

}