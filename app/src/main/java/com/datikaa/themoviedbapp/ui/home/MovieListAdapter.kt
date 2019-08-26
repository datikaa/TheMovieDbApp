package com.datikaa.themoviedbapp.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.datikaa.themoviedbapp.PicSizeW500
import com.datikaa.themoviedbapp.PicassoBaseUrl
import com.datikaa.themoviedbapp.api.model.UpcomingMovie
import com.datikaa.themoviedbapp.R
import com.datikaa.themoviedbapp.common.inflate
import com.datikaa.themoviedbapp.ui.detail.DetailFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_card.view.*
import androidx.recyclerview.widget.DiffUtil



class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var list: List<UpcomingMovie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.movie_card)
        return ViewHolder(view).apply {
            itemView.setOnClickListener {
                list[adapterPosition].id.let { movieId ->
                    val extras = FragmentNavigatorExtras(
                        itemView.imageView_background to "imageView_background_transition",
                        itemView.movie_title to "textView_movieTitle_transition")
                    val bundle = DetailFragment.getBundle(movieId.toString())
                    it.findNavController().navigate(R.id.openDetailFragmentFromHome, bundle, null, extras)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun dispatchChanges(upcomingMovies: List<UpcomingMovie>) {
        val diffCallback = DiffUtilUpcomingMovies(upcomingMovies, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        list = upcomingMovies
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: UpcomingMovie) {
            itemView.movie_title.text = movie.title ?: "Error"
            itemView.movie_id.text = movie.id?.toString() ?: "Error"

            Picasso.get()
                .load(PicassoBaseUrl + PicSizeW500 + movie.backdrop_path)
                .placeholder(R.drawable.pic_loading_placeholder)
                .into(itemView.imageView_background)
        }
    }
}