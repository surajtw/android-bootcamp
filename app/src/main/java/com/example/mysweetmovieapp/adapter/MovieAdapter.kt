package com.example.mysweetmovieapp.adapter

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mysweetmovieapp.MovieListActivity
import com.example.mysweetmovieapp.R
import com.example.mysweetmovieapp.model.Movie
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.net.URL

class MovieAdapter(private val context: MovieListActivity, private val movieList: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list_jtem, parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movie = movieList.get(position);
        holder.movieNameTextView?.text = movie.name;
        holder.movieRating?.text = movie.rating.toString()
        holder.movieImageView?.setImageResource( com.google.android.material.R.drawable.ic_keyboard_black_24dp)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, movieList.get(position).name, Toast.LENGTH_SHORT).show()
        }

        class PicassoHandler(): Callback {
            override fun onSuccess() {
                Log.d("Image loded","")
            }

            override fun onError(e: Exception?) {
                Log.e("Error loading image : ", e?.stackTraceToString() ?: "");
            }
        }

//        Picasso.
//            get()
//            .load(movie.image)
//            .error( R.drawable.ic_launcher_foreground )
//            .placeholder(R.drawable.ic_launcher_background)
//            .into(holder.movieImageView, PicassoHandler());
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieNameTextView = view.findViewById<TextView>(R.id.movie_name);
        val movieImageView = view.findViewById<ImageView>(R.id.movie_image);
        val movieRating = view.findViewById<TextView>(R.id.movie_rating)
    }


}
