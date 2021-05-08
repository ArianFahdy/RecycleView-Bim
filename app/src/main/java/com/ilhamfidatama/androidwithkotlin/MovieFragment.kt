package com.ilhamfidatama.androidwithkotlin

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamfidatama.androidwithkotlin.db.DatabaseLocalManager
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.movie_item.*

class MovieFragment : Fragment() {

    private var movies = mutableListOf<Movie>()
    private var adapter : MovieAdapter? = null
    private val movieManager by lazy {
        DatabaseLocalManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_movie.layoutManager = LinearLayoutManager(view.context)

        movies.clear()
        movies.addAll(movieManager.getMovies())

        adapter = MovieAdapter(::onDelete, movies)

        rv_movie.setHasFixedSize(true)
        rv_movie.adapter = adapter

    }

    private fun onDelete(position : Int, movie : Movie){
        movieManager.delete(movie.name)
        movies.removeAt(position)
        adapter?.notifyDataSetChanged()
        Toast.makeText(view?.context, "${movie.name} berhasil di hapus", Toast.LENGTH_SHORT).show()
    }

}