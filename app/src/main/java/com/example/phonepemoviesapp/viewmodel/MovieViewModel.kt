package com.example.phonepemoviesapp.viewmodel

import android.app.Application
import com.example.phonepemoviesapp.network.MovieRepository.hitApi
import androidx.lifecycle.AndroidViewModel
import com.example.phonepemoviesapp.viewmodel.MovieViewModel
import com.example.phonepemoviesapp.network.MovieRepository
import androidx.lifecycle.MutableLiveData
import com.example.phonepemoviesapp.model.MovieData
import androidx.lifecycle.LiveData
import com.example.phonepemoviesapp.util.MainConstant
import com.example.phonepemoviesapp.network.MovieRepository.ISuccessResponseListener
import com.google.gson.JsonObject
import com.google.gson.Gson
import java.lang.Exception

class MovieViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val mRepository: MovieRepository
    private var mResponse: MutableLiveData<MovieData?>? = null
    fun hitApi(): LiveData<MovieData?> {
        val url = MainConstant.url
        mResponse = MutableLiveData()
        mRepository.hitApi(
            url,
            object : ISuccessResponseListener {
                override fun onSuccess(stringData: String?, response: JsonObject?) {
                    try {
                        val movieResponse = Gson().fromJson(stringData, MovieData::class.java)
                        mResponse!!.setValue(movieResponse)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        mResponse!!.setValue(null)
                    }
                }
            })
        return mResponse!!
    }

    init {
        mRepository = MovieRepository(application!!)
    }
}