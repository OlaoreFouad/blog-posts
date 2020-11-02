package dev.olaore.linespathsandarcs.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class LinedPathView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr) {

    var path = Path()
    var pathPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.parseColor("#a20a0a") }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawLinedPath(canvas)
    }

    private fun drawLinedPath(canvas: Canvas?) {

        path.moveTo(0.1f * width, 0.1f * height)
        path.lineTo(0.1f * width, 0.5f * height)
        path.lineTo(0.9f * width, 0.1f * height)
        path.lineTo(0.9f * width, 0.5f * height)
        path.lineTo(0.1f * width, 0.1f * height)

        canvas?.drawPath(path, pathPaint)

    }

}