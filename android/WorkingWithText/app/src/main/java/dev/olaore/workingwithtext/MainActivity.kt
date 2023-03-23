package dev.olaore.workingwithtext

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.annotation.ColorInt
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.skydoves.colorpickerview.ColorPickerView
import dev.olaore.workingwithtext.views.CustomTextAlign
import dev.olaore.workingwithtext.views.CustomTextView
import dev.olaore.workingwithtext.views.CustomTextView.*

class MainActivity : AppCompatActivity() {

    private lateinit var changeColorButton: Button
    private lateinit var customTextView: CustomTextView
    private lateinit var selectedColorView: View
    private lateinit var customTextEditText: EditText
    private lateinit var textSizeInput: EditText

    private lateinit var textAlignmentGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeColorButton = findViewById(R.id.change_color_button)
        customTextView = findViewById(R.id.custom_text_view)
        selectedColorView = findViewById(R.id.selected_color_view)
        customTextEditText = findViewById(R.id.custom_text_edit_text)
        textSizeInput = findViewById(R.id.text_size_input)

        textAlignmentGroup = findViewById(R.id.text_alignment_radio_group)

        updateSelectedColorBox(customTextView.currentTextColor)

        initializeViews()
    }

    private fun initializeViews() {
        changeColorButton.setOnClickListener {
            openColorPicker(customTextView.currentTextColor)
        }

        customTextEditText.addTextChangedListener {
            val text = it.toString()
            setText(text)
        }

        textSizeInput.addTextChangedListener {
            val size = it.toString()
            if (size.isNotEmpty()) {
                setTextSize(size.toInt())
            }
        }

        textAlignmentGroup.setOnCheckedChangeListener { _, checkedId ->
            val textAlign = when (checkedId) {
                R.id.left_align -> CustomTextAlign.LEFT
                R.id.right_align -> CustomTextAlign.RIGHT
                R.id.center_align -> CustomTextAlign.CENTER
                else -> CustomTextAlign.CENTER
            }

            setTextAlign(textAlign)
        }
    }

    private fun setTextColor(@ColorInt color: Int?) {
        color?.let { _color ->
            customTextView.setTextColor(color)
            updateSelectedColorBox(_color)
        }
    }

    private fun setTextSize(updatedSize: Int) {
        if (updatedSize >= 0) {
            customTextView.setTextSize(updatedSize)
        }
    }

    private fun setText(text: String) {
        customTextView.setText(text)
    }

    private fun setTextAlign(alignment: CustomTextAlign) {
        customTextView.setTextAlignment(alignment)
    }

    private fun updateSelectedColorBox(color: Int?) {
        color?.let { _color ->
            selectedColorView.setBackgroundColor(_color)
        }
    }

    @SuppressLint("InflateParams")
    private fun openColorPicker(@ColorInt currentColor: Int? = null) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("Select color")
            val view = layoutInflater.inflate(R.layout.dialog_color_picker, null)
            val colorPicker = view.findViewById<ColorPickerView>(R.id.colorPickerView)
            currentColor?.let { _color ->
                colorPicker.setInitialColor(_color)
            }
            setView(view)
            setPositiveButton("Set Color") { dialog, _ ->
                val selectedColor = colorPicker.color
                setTextColor(selectedColor)
                dialog.dismiss()
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
        }

        val dialog = builder.create()
        dialog.show()
    }

}