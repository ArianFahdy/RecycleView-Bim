package com.ilhamfidatama.androidwithkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter (val onPress : (Int, Movie) -> Unit,
                    private val listMovie : List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val onPress : (Int, Movie) -> Unit,
                                itemView: View) : RecyclerView.ViewHolder(itemView){

        val imgMovie = itemView.findViewById<ImageView>(R.id.img_movie)
        val nameMovie = itemView.findViewById<TextView>(R.id.tv_name)
        val popularMovie = itemView.findViewById<TextView>(R.id.tv_popularity)
        val btn_delete = itemView.findViewById<ImageView>(R.id.img_delete)

        fun bind (movie : Movie){
            nameMovie.text = movie.name
            popularMovie.text = movie.popularity.toString()
            Glide.with(itemView.context)
                    .load(movie.imagePath)
                    .into(imgMovie)

            btn_delete.setOnClickListener {
                onPress(adapterPosition, movie)
                true
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(onPress, view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size
}