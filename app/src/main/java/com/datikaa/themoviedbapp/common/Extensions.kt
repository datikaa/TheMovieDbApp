package com.datikaa.themoviedbapp.common

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * Convenience extension method for layout inflation
 */
fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false) : View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

/**
 * convert Int to Dp
 */
fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

/**
 * convert Int to Px
 */
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()