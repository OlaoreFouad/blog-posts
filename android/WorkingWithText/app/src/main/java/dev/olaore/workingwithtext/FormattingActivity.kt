package dev.olaore.workingwithtext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.olaore.workingwithtext.views.CustomTextView

class FormattingActivity : AppCompatActivity() {

    private lateinit var customTextView: CustomTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formatting)

        customTextView = findViewById(R.id.custom_text_view)

        customTextView.setText("Default Text")
    }
}