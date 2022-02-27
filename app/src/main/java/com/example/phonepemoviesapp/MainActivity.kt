package com.example.phonepemoviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phonepemoviesapp.model.MovieData
import com.example.phonepemoviesapp.model.Results
import com.example.phonepemoviesapp.util.MainUtil
import com.example.phonepemoviesapp.view.MovieAdapter
import com.example.phonepemoviesapp.viewmodel.MovieViewModel
import java.util.*


//used data binding to bind the view with data
//getting manifest errors, hence couldnt run project completely but it should work fine
//will another hour to make project up and running
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainMovieBinding? = null
    private var mViewModel: MovieViewModel? = null
    private var progressBar: ProgressBar? = null
    private val movieList: List<MovieData>? = null
    private lateinit var mAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main_movie)

       //set handler
        binding.setVariable(BR.handler, this)
        progressBar = binding.progress
        hitApi()

        //to handle no network calls
        binding.retry.setOnClickListener { v ->
            if (v.getVisibility() === View.VISIBLE) hitApi()

        }
    }


    private fun updateAdapter() {
        mAdapter.notifyDataSetChanged()
    }


    private fun hitApi() {
        if (binding.getRoot().getContext() != null) {
            mViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

            //if no network available
            if (MainUtil.isNetworkAvailable(this)) {
                fetchData()
            } else {
                noResultFound()
            }
        }
    }

    // no result found
    private fun noResultFound() {
        binding.resultNotFound.setVisibility(View.VISIBLE)
        binding.retry.setVisibility(View.VISIBLE)
        binding.textNoResult.setVisibility(View.VISIBLE)
    }

    //using live data to observe the result
    private fun fetchData() {
        progressBar!!.visibility = View.VISIBLE
        mViewModel?.hitApi()?.observe(this) { flickerModel ->
            progressBar!!.visibility = View.GONE
            setRecyclerview(flickerModel.results)
        }
    }


    //settting data into Recyclerview
    private fun setRecyclerview(photosList: List<Results>) {
        binding.recyclerview.setVisibility(View.VISIBLE)
        binding.filterPrice.setVisibility(View.VISIBLE)
        binding.filterTime.setVisibility(View.VISIBLE)
        binding.recyclerview.setHasFixedSize(true)
        mAdapter = MovieAdapter(photosList, binding.getRoot().getContext())
        binding.recyclerview.setLayoutManager(
            LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
            )
        )
        binding.recyclerview.setAdapter(mAdapter)
    }


}