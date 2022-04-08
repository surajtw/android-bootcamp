package com.example.mysweetmovieapp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mysweetmovieapp.MovieDetail
import com.example.mysweetmovieapp.MovieListActivity
import com.example.mysweetmovieapp.R
import com.example.mysweetmovieapp.model.Movie
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class MovieAdapter(private val context: MovieListActivity, private var movieList: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list_jtem, parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movie = movieList[position];
        holder.movieNameTextView?.text = movie.content.title;
        holder.movieRatingTextView?.text = movie.content.rating;
        holder.itemView.setOnClickListener {
//            Toast.makeText(context, movieList.get(position).content.title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, MovieDetail::class.java);
            intent.putExtra("movie", movie);
            context.startActivity(intent);
        }

        class PicassoHandler(): Callback {
            override fun onSuccess() {
                Log.d("Image loaded", "");
            }

            override fun onError(e: Exception?) {
                Log.e("Error loading image : ", e?.stackTraceToString() ?: "");
            }
        }

        Picasso.
            get()
            .load(movie.content.movie_logo)
            .error( R.drawable.ic_launcher_foreground )
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.movieImageView, PicassoHandler());
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieNameTextView = view.findViewById<TextView>(R.id.movie_name);
        val movieRatingTextView = view.findViewById<TextView>(R.id.movie_rating);
        val movieImageView = view.findViewById<ImageView>(R.id.movie_image);
    }

    fun setMovieListItems(movieList: List<Movie>){
        this.movieList = movieList as ArrayList<Movie>
        notifyDataSetChanged()
    }

}
