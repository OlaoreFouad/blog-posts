package dev.olaore.compoundcmpts_final

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_FILE_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        select_file_button.setOnClickListener {
            selectFile()
        }

    }

    private fun selectFile() {
        val selectFileIntent = Intent(Intent.ACTION_GET_CONTENT)
        selectFileIntent.type = "*/*"
        startActivityForResult(selectFileIntent, REQUEST_FILE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_FILE_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val fileUri = data.data
            }
        }
    }

}
