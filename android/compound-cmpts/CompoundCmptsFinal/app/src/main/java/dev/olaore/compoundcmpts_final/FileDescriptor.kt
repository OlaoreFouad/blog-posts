package dev.olaore.compoundcmpts_final

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.file_descriptor.view.*
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import kotlin.math.absoluteValue

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

    var showInfoByDefault = false
    @DrawableRes var viewBackground: Int = R.drawable.round_file_descriptor_background

    init {

        // get the inflater service from the android system
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // the array of attributes
        val attributes = ctx.obtainStyledAttributes(attributeSet, R.styleable.FileDescriptor)

        showInfoByDefault = attributes.getBoolean(R.styleable.FileDescriptor_showInfoByDefault, showInfoByDefault) // can be set to false
        viewBackground = attributes.getResourceId(R.styleable.FileDescriptor_viewBackground, viewBackground) // can be set to
        // R.drawable.round_file_descriptor_background

        attributes.recycle()

        // inflate the layout into "this" layout
        inflater.inflate(R.layout.file_descriptor, this)

        // finally, we set the background of our component
        setBackgroundResource(viewBackground)
        file_info.visibility = if (showInfoByDefault) View.VISIBLE else View.GONE

        info_file_image.setOnClickListener {
            showInfoByDefault = !showInfoByDefault
            file_info.visibility = if (showInfoByDefault) View.VISIBLE else View.GONE
        }

        share_file_image.setOnClickListener { shareDescription() }

        // TODO: run the app, get the app design in an image and set in the blog post.
        // TODO: set up sharing in the app too, if a file has not been selected the info and share button should not be visible - invisible

    }

    @SuppressLint("SetTextI18n")
    private fun setUpFileDescriptor() {
        setFile()
        setUpFileType()

        file?.let {
            file_name.text = it.name
            file_info.text = """
                File Name: ${ it.name }
                Path To File: ${ it.path }
                Last Modified: ${ it.lastModified().format("MMM dd, YYYY hh:mm") }
                Size: ${ it.length().valueInKb() }KB
            """.trimIndent()
        }

        retrieveThumbnail()

    }

    private fun retrieveThumbnail() {

        try {

            when(fileType) {
                FileType.IMAGE -> ThumbnailGenerator.createImageThumbnail(ctx, fileUri, file_preview_image)
                FileType.MP4 -> ThumbnailGenerator.createVideoThumbnail(ctx, fileUri, file_preview_image)
                FileType.DOCX -> file_preview_image.setImageResource(R.drawable.docx)
                FileType.MP3 -> file_preview_image.setImageResource(R.drawable.mp3)
                FileType.PDF -> file_preview_image.setImageResource(R.drawable.pdf)
                FileType.TEXT -> file_preview_image.setImageResource(R.drawable.txt)
                FileType.UNKNOWN -> file_preview_image.setImageResource(R.drawable.no_file_selected)
            }

        } catch (e: Exception) {
            Log.d("FileDescriptor", "Error occured: ${ e.message }")
        }

    }

    private fun shareDescription() {
        file?.let {

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, file_info.text)
                type = "text/plain"
            }
            ctx.startActivity(Intent.createChooser(shareIntent, null))

            return
        }

        Toast.makeText(ctx, "You have to select a File first", Toast.LENGTH_LONG).show()
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
            Log.d("FileDescriptor", "Path: $filePath")
            filePath?.let { path ->
                file = File(path)
            }
        }

    }

}


enum class FileType {
    IMAGE, TEXT, PDF, DOCX, MP4, MP3, UNKNOWN
}

fun Long.format(format: String): String {
    return SimpleDateFormat(format).format(this.absoluteValue)
}

fun Long.valueInKb(): Double {
    val kb: Double = this.div(1024).toDouble()
    return kb
}