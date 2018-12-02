package com.datikaa.themoviedbapp.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.datikaa.themoviedbapp.api.model.UpcomingMovie

class DiffUtilUpcomingMovies(private val oldList: List<UpcomingMovie>, private val newList: List<UpcomingMovie>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}