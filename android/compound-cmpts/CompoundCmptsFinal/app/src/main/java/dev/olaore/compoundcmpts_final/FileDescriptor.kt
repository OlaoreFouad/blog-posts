package dev.olaore.compoundcmpts_final

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.file_descriptor.view.*
import java.io.File

class FileDescriptor @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : ConstraintLayout(ctx, attributeSet, defStyleAttr) {

    var fileUri: Uri? = null
        set(value) {
            field = value
            setUpFileDescriptor()
        }

    var file: File? = null
    var fileType: FileType? = null

    init {

        // get the inflater service from the android system
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // inflate the layout into "this" layout
        inflater.inflate(R.layout.file_descriptor, this)

        // finally, we set the background of our component
        setBackgroundResource(R.drawable.round_file_descriptor_background)

    }

    private fun setUpFileDescriptor() {
        setFile()
        setUpFileType()
    }

    private fun setUpFileTypeImage() {
        file_type_image.setImageResource(when(fileType) {
            FileType.DOCX -> R.drawable.docx
            FileType.IMAGE -> R.drawable.image
            FileType.MP3 -> R.drawable.mp3
            FileType.MP4 -> R.drawable.video
            FileType.PDF -> R.drawable.pdf
            FileType.TEXT -> R.drawable.txt
            FileType.UNKNOWN -> R.drawable.no_file_selected
            else -> R.drawable.no_file_selected
        })
    }

    private fun setUpFileType() {
        val resolver = ctx.contentResolver
        val type = resolver.getType(fileUri!!)!!

        fileType = if (type.contains("image")) {
            FileType.IMAGE
        } else if (type.contains("video")) {
            FileType.MP4
        } else if (type.contains("application/msword") || type.contains("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
            FileType.DOCX
        } else if (type.contains("audio")) {
            FileType.MP3
        } else if (type.contains("application/pdf")) {
            FileType.PDF
        } else if (type.contains("text/plain")) {
            FileType.TEXT
        } else {
            FileType.UNKNOWN
        }

        setUpFileTypeImage()
        }


    private fun setFile() {
        // columns to be retrieved from the media content provider
        val columns = arrayOf(MediaStore.Images.Media.DATA)
        // query the content resolver for the data associated with the fileUri passed in
        val cursor = ctx.contentResolver.query(
            fileUri!!, columns, null, null, null
        )


        cursor?.let {
            // move to the first item
            it.moveToFirst()
            // get the index for the data column on the content provider
            val dataColumnIndex = it.getColumnIndex(columns[0])
            // get the string value at the index
            val filePath = cursor.getString(dataColumnIndex)
            // close the cursor
            it.close()
            filePath?.let { path ->
                file = File(path)
            }
        }

    }

}


enum class FileType {
    IMAGE, TEXT, PDF, DOCX, MP4, MP3, UNKNOWN
}