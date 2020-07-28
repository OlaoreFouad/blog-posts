package dev.olaore.spannablesdemo

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.*
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var boldText: TextView
    private lateinit var foregroundText: TextView
    private lateinit var foregroundBackgroundText: TextView
    private lateinit var sizingText: TextView
    private lateinit var sizingBiggerText: TextView
    private lateinit var boldStrikethroughUnderlineText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boldText = findViewById(R.id.bold_text)
        foregroundText = findViewById(R.id.foreground_text)
        foregroundBackgroundText = findViewById(R.id.foreground_and_background_text)
        sizingText = findViewById(R.id.sizing_text)
        sizingBiggerText = findViewById(R.id.sizing_bigger_text)
        boldStrikethroughUnderlineText = findViewById(R.id.bold_strikethrough_underline_text)

        setBoldText()
        setForegroundText()
        setForegroundAndBackground()
        setSizingText()
        setSizingBiggerText()
        setBoldStrikeThroughUnderlineText()

    }

    private fun setBoldStrikeThroughUnderlineText() {
        val boldText = "This text is bold"
        val boldStrUnderlineSpannable = SpannableStringBuilder(boldText)

        val boldStartIndex = boldText.indexOf("bold")
        val amountOfCharactersBold = "bold".length

        boldStrUnderlineSpannable.setSpan(
            StyleSpan(Typeface.BOLD), boldStartIndex, boldStartIndex + amountOfCharactersBold, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val strikethroughText = " and this is a strikethrough"
        boldStrUnderlineSpannable.append(strikethroughText)

        val strikethroughStartIndex = boldStrUnderlineSpannable.indexOf("strikethrough")
        val amountOfCharacterstrikethrough = "strikethrough".length

        boldStrUnderlineSpannable.setSpan(
            StrikethroughSpan(), strikethroughStartIndex, strikethroughStartIndex + amountOfCharacterstrikethrough, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val underlineText = " and this is an underlined text"
        boldStrUnderlineSpannable.append(underlineText)

        val underlineStartIndex = boldStrUnderlineSpannable.indexOf("underlined")
        val amountOfCharacterUnderline = "underlined".length

        boldStrUnderlineSpannable.setSpan(
            UnderlineSpan(), underlineStartIndex, underlineStartIndex + amountOfCharacterUnderline, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        boldStrikethroughUnderlineText.text = boldStrUnderlineSpannable

    }

    private fun setSizingText() {
        val text = "This text is smaller than the rest"
        val sizingSpannableText = SpannableString(text)

        val startIndex = text.indexOf("smaller")
        val amountOfCharacters = "smaller".length

        sizingSpannableText.setSpan(
            AbsoluteSizeSpan(20), startIndex, startIndex + amountOfCharacters, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        sizingText.text = sizingSpannableText

    }

    private fun setSizingBiggerText() {
        val text = "This text is double the size of the rest"
        val sizingSpannableText = SpannableString(text)

        val startIndex = text.indexOf("double the size")
        val amountOfCharacters = "double the size".length

        sizingSpannableText.setSpan(
            RelativeSizeSpan(2f), startIndex, startIndex + amountOfCharacters, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        sizingBiggerText.text = sizingSpannableText

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
