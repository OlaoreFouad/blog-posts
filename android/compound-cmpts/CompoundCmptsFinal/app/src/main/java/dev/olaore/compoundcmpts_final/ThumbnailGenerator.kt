package dev.olaore.compoundcmpts_final

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import com.bumptech.glide.Glide

object ThumbnailGenerator {

    fun createVideoThumbnail(context: Context, imageUri: Uri?, imageView: ImageView) {

        Glide.with(context)
            .asBitmap()
            .load(imageUri)
            .into(imageView)

    }

    fun createImageThumbnail(context: Context, imageUri: Uri?, imageView: ImageView) {

        imageView.setImageBitmap(
            MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
        )

    }

}