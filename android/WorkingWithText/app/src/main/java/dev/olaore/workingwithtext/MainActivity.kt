package dev.olaore.workingwithtext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var changeColorButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeColorButton = findViewById(R.id.change_color_button)

        changeColorButton.setOnClickListener {
            openColorPicker()
        }
    }

    private fun openColorPicker(currentColor: Int? = null) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("Select color")
            val view = layoutInflater.inflate(R.layout.dialog_color_picker, null)
            setView(view)
            setPositiveButton("Set Color") { dialog, _ ->
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