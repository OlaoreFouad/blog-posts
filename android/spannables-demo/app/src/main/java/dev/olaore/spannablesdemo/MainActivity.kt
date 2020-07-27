package dev.olaore.spannablesdemo

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var boldText: TextView
    private lateinit var foregroundText: TextView
    private lateinit var foregroundBackgroundText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boldText = findViewById(R.id.bold_text)
        foregroundText = findViewById(R.id.foreground_text)
        foregroundBackgroundText = findViewById(R.id.foreground_and_background_text)

        setBoldText()
        setForegroundText()
        setForegroundAndBackground()

    }

    private fun setForegroundAndBackground() {
        val text = "Simulating highlighted text"
        val fgBgSpannableText = SpannableString(text)

        val startIndex = fgBgSpannableText.indexOf("highlighted")
        val amountOfCharacters = "highlighted".length

        fgBgSpannableText.setSpan(
            BackgroundColorSpan(Color.YELLOW), startIndex, startIndex + amountOfCharacters, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        fgBgSpannableText.setSpan(
            ForegroundColorSpan(Color.BLUE), startIndex, startIndex + amountOfCharacters, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        foregroundBackgroundText.text = fgBgSpannableText

    }

    private fun setForegroundText() {
        val text = "A part of this text would be set to color red"
        val foregroundSpannableText = SpannableString(text)

        val startIndex = foregroundSpannableText.indexOf("part of this text")
        val amountOfCharacters = "part of this text".length

        val redStartIndex = foregroundSpannableText.indexOf("red")
        val redAmountOfCharacters = 3 // length of "red"

        foregroundSpannableText.setSpan(
            ForegroundColorSpan(Color.RED), startIndex, startIndex + amountOfCharacters, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        foregroundSpannableText.setSpan(
            ForegroundColorSpan(Color.RED), redStartIndex, redStartIndex + redAmountOfCharacters, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        foregroundText.text = foregroundSpannableText

    }

    private fun setBoldText() {
        val text = "This is a bold text"
        val boldSpannableText = SpannableString(text)

        val startIndex = boldSpannableText.indexOf("bold") // get the startindex of the text to be spanned
        val amountOfCharacters = 4 // 4 letters of the bold text

        boldSpannableText.setSpan(
            StyleSpan(Typeface.BOLD), startIndex, startIndex + amountOfCharacters, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        boldText.text = boldSpannableText
    }


}
