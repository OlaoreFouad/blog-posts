package dev.olaore.compoundcmpts_final

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout

class FileDescriptor @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : ConstraintLayout(ctx, attributeSet, defStyleAttr) {

    init {

        // get the inflater service from the android system
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // inflate the layout into "this" layout
        inflater.inflate(R.layout.file_descriptor, this)

        // finally, we set the background of our component
        setBackgroundResource(R.drawable.round_file_descriptor_background)

    }

}