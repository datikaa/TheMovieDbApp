package com.datikaa.themoviedbapp.ui.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.datikaa.themoviedbapp.common.toPx

class HomeListItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition == RecyclerView.NO_POSITION) return
        val itemCount = state.itemCount

        outRect.top = 10.toPx()
        outRect.left = 10.toPx()
        outRect.right = 10.toPx()
        if (itemPosition == itemCount - 1) {
            outRect.bottom = 10.toPx()
        }
    }
}