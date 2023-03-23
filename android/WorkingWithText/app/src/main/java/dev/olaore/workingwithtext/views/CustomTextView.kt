package dev.olaore.workingwithtext.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import dev.olaore.workingwithtext.toDp

const val DEFAULT_TEXT_SIZE = 24f

enum class CustomTextAlign(val value: Paint.Align) {
    LEFT(Paint.Align.LEFT),
    CENTER(Paint.Align.CENTER),
    RIGHT(Paint.Align.RIGHT)
}

enum class FormattingStyle {
    BOLD, UNDERLINED, STRIKETHROUGH,
}

enum class TextStyle(val value: Paint.Style) {
    FILL(Paint.Style.FILL),
    STROKE(Paint.Style.STROKE),
    FILL_AND_STROKE(Paint.Style.FILL_AND_STROKE),
}

enum class TextFont(val value: Typeface) {
    SERIF(Typeface.SERIF),
    SANS_SERIF(Typeface.SANS_SERIF),
    MONOSPACE(Typeface.MONOSPACE),
}

class CustomTextView @JvmOverloads constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet? = null,
    private val defStyleAttr: Int = 0,
    private val defStyleRes: Int = 0
) : View(ctx, attributeSet, defStyleAttr, defStyleRes) {

    var currentTextColor: Int? = Color.BLACK
        private set
    private var currentTextSize: Float = toDp(DEFAULT_TEXT_SIZE)
    private var currentText: String = ""
    private var currentTextAlign = CustomTextAlign.CENTER.value

    private val textBounds = Rect()
    private val textPaint = Paint().apply {
        textAlign = Paint.Align.CENTER
        textSize = toDp(DEFAULT_TEXT_SIZE)
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

    fun setTextColor(@ColorInt color: Int) {
        this.currentTextColor = color
        textPaint.color = color
        invalidate()
    }

    fun setTextSize(updatedSize: Int) {
        this.currentTextSize = toDp(updatedSize.toFloat())
        textPaint.textSize = this.currentTextSize
        invalidate()
    }

    fun setText(text: String) {
        this.currentText = text
        invalidate()
    }

    fun setTextAlignment(alignment: CustomTextAlign) {
        this.currentTextAlign = alignment.value
        textPaint.textAlign = this.currentTextAlign
        invalidate()
    }

    fun setTextSpacing(spacing: Int) {
        textPaint.letterSpacing = spacing.toFloat()
        invalidate()
    }

    fun setTextFormattingStyle(style: FormattingStyle, isChecked: Boolean) {
        when (style) {
            FormattingStyle.BOLD -> {
                textPaint.isFakeBoldText = isChecked
            }
            FormattingStyle.UNDERLINED -> {
                textPaint.isUnderlineText = isChecked
            }
            FormattingStyle.STRIKETHROUGH -> {
                textPaint.isStrikeThruText = isChecked
            }
        }

        invalidate()
    }

    fun setTextStyle(style: TextStyle) {
        textPaint.style = style.value
        invalidate()
    }

    fun setTextFont(textFont: TextFont) {
        textPaint.typeface = textFont.value
        invalidate()
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
        textPaint.getTextBounds(this.currentText, 0, this.currentText.length, textBounds)

        val textY = height / 2
        val textX = when (textPaint.textAlign) {
            Paint.Align.CENTER -> {
                (width / 2)
            }
            Paint.Align.LEFT -> {
                0
            }
            Paint.Align.RIGHT -> {
                width
            }
        }

        canvas.drawText(this.currentText, textX.toFloat(), textY.toFloat(), textPaint)
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