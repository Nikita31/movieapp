package com.example.phonepemoviesapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.phonepemoviesapp.R
import com.example.phonepemoviesapp.model.Results

class MovieAdapter(list: List<Results>, context: Context) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movieList: List<Results>? = null
    private var mContext: Context? = null
    private var view: View? = null

    init {
        movieList = list
        mContext = context
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i: Int): MovieViewHolder {
         view = LayoutInflater.from(viewGroup.context).inflate(R.layout.movie_item, viewGroup, false);
        return MovieViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.movie_item, viewGroup, false))
    }

    override fun onBindViewHolder(@NonNull viewHolder: MovieViewHolder, i: Int) {
        viewHolder.bind(movieList!![i], viewHolder)
    }

    override fun getItemCount(): Int {
        return if (movieList != null) movieList!!.size else 0
    }
}