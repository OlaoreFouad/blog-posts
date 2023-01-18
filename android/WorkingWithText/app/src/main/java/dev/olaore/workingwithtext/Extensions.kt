package dev.olaore.workingwithtext

import android.util.TypedValue
import android.view.View

fun View.toDp(value: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics
    )
}