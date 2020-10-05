package dev.olaore.customviewsintro

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class MyFirstCustomView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr) {

    private val colors = listOf<Int>(Color.RED, Color.YELLOW, Color.DKGRAY, Color.BLACK, Color.MAGENTA)

//    create the paint object for the rectangle
    private val rectPaint = Paint()
//    create the paint object for the circle
    private val circlePaint = Paint()

    //    create the outlined paint object for the rectangle
    private val outlinedRectPaint = Paint()
    //    create the outlined paint object for the circle
    private val outlinedCirclePaint = Paint()

    init {
//        set the color to blue
        rectPaint.color = Color.BLUE
//        smoothen the edges using antiAlias flag
        rectPaint.isAntiAlias = true

//        set the color to red
        circlePaint.color = Color.RED
//        smoothen the edges using antiAlias flag
        circlePaint.isAntiAlias = true

//        set the color of the paint object
        outlinedRectPaint.color = Color.GREEN
//        set the style of paint to be used
        outlinedRectPaint.style = Paint.Style.STROKE
//        set the width of the stroke
        outlinedRectPaint.strokeWidth = 4f

        outlinedCirclePaint.style = Paint.Style.STROKE
        outlinedCirclePaint.strokeWidth = 4f
        outlinedCirclePaint.color = Color.BLACK

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawRectangle(canvas)
        drawCircle(canvas)

//        make outlined draw calls
        drawOutlinedRectangle(canvas)
        drawOutlinedCircle(canvas)
    }

    private fun getRandomIndex(): Int {
        return Random.nextInt(0, colors.size - 1)
    }

    private fun drawRectangle(canvas: Canvas?) {
        canvas?.drawRect(0f, 0f, 150f, 150f, rectPaint)
    }

    private fun drawCircle(canvas: Canvas?) {
        canvas?.drawCircle(
            (width - 75f), 75f, 75f, circlePaint
        )
    }

    private fun drawOutlinedRectangle(canvas: Canvas?) {

//        translate the canvas
        canvas?.translate(0f, 160f)

//        draw rectangle
        canvas?.drawRect(0f, 0f, 150f, 150f, outlinedRectPaint)
    }

    private fun drawOutlinedCircle(canvas: Canvas?) {

//        translate the canvas
        canvas?.translate((width - 75f), 75f)

        canvas?.drawCircle(
            0f, 0f, 75f, outlinedCirclePaint
        )
    }

    fun toggle() {
        rectPaint.color = colors[getRandomIndex()]
        circlePaint.color = colors[getRandomIndex()]
        outlinedRectPaint.color = colors[getRandomIndex()]
        outlinedCirclePaint.color = colors[getRandomIndex()]

        invalidate()
    }

}

