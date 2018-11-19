package com.datikaa.themoviedbapp.ui

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
import com.datikaa.themoviedbapp.fragment.DetailFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_card.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var list: List<UpcomingMovie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.movie_card)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: UpcomingMovie) {
            itemView.movie_title.text = movie.title ?: "Error"
            itemView.movie_id.text = movie.id?.toString() ?: "Error"

            Picasso.get().load(PicassoBaseUrl + PicSizeW500 + movie.backdrop_path).into(itemView.imageView_background)

            itemView.setOnClickListener {
                movie.id.let { movieId ->
                    val extras = FragmentNavigatorExtras(
                        itemView.imageView_background to "imageView_background_transition",
                        itemView.movie_title to "textView_movieTitle_transition")
                    val bundle = DetailFragment.getBundle(movieId.toString())
                    it.findNavController().navigate(R.id.openListFragmentFromHome, bundle, null, extras)
                }
            }
        }
    }
}