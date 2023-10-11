package dev.olaore.workingwithtext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import dev.olaore.workingwithtext.views.CustomTextView
import dev.olaore.workingwithtext.views.FormattingStyle
import dev.olaore.workingwithtext.views.TextFont
import dev.olaore.workingwithtext.views.TextStyle

class FormattingActivity : AppCompatActivity() {

    private lateinit var customTextView: CustomTextView

    private lateinit var spacingSeekbar: SeekBar

    // formatting checkboxes.
    private lateinit var boldCheckbox: CheckBox
    private lateinit var underlineCheckbox: CheckBox
    private lateinit var strikethroughCheckbox: CheckBox

    // fill radiogroup
    private lateinit var fillGroup: RadioGroup

    // fonts radiogroup
    private lateinit var fontsGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formatting)

        customTextView = findViewById(R.id.custom_text_view)

        customTextView.setText("Default Text")

        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        spacingSeekbar = findViewById(R.id.spacing_seekbar)

        boldCheckbox = findViewById(R.id.bold_checkbox)
        underlineCheckbox = findViewById(R.id.underline_checkbox)
        strikethroughCheckbox = findViewById(R.id.strikethrough_checkbox)

        fillGroup = findViewById(R.id.text_fill_radio_group)
        fontsGroup = findViewById(R.id.text_fonts_radio_group)
    }

    private fun setListeners() {
        spacingSeekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                customTextView.setTextSpacing(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        boldCheckbox.setOnCheckedChangeListener { _, isChecked ->
            customTextView.setTextFormattingStyle(FormattingStyle.BOLD, isChecked)
        }

        underlineCheckbox.setOnCheckedChangeListener { _, isChecked ->
            customTextView.setTextFormattingStyle(FormattingStyle.UNDERLINED, isChecked)
        }

        strikethroughCheckbox.setOnCheckedChangeListener { _, isChecked ->
            customTextView.setTextFormattingStyle(FormattingStyle.STRIKETHROUGH, isChecked)
        }

        fillGroup.setOnCheckedChangeListener { _, checkedId ->
            val textStyle = when (checkedId) {
                R.id.apply_fill -> TextStyle.FILL
                R.id.apply_stroke -> TextStyle.STROKE
                R.id.apply_fill_and_stroke -> TextStyle.FILL_AND_STROKE
                else -> TextStyle.FILL
            }

            customTextView.setTextStyle(textStyle)
        }

        fontsGroup.setOnCheckedChangeListener { _, checkedId ->
            val textFont = when (checkedId) {
                R.id.apply_serif -> TextFont.SERIF
                R.id.apply_sans_serif -> TextFont.SANS_SERIF
                R.id.apply_monospace -> TextFont.MONOSPACE
                else -> TextFont.SERIF
            }

            customTextView.setTextFont(textFont)
        }
    }
}