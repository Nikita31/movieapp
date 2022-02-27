package com.example.phonepemoviesapp.network

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import com.google.gson.JsonObject
import java.lang.Exception

class MovieRepository(application: Application) {

    private var networkService: MovieNetworkService? = null
    private var mContext: Context? = null

    interface ISuccessResponseListener {
        fun onSuccess(stringData: String?, response: JsonObject?)
    }

    fun MovieRepository(context: Context?) {
        networkService = MovieNetworkService()
        mContext = context
    }

    fun hitApi(url: String?, @NonNull callback: ISuccessResponseListener) {
        try {
            networkService?.fetch(mContext, url, MovieNetworkService.Method.GET, null,
                null, object : MovieNetworkService.IResponseListener {
                    override fun onSuccess(stringData: String?, response: JsonObject?) {
                        Log.d("Success", "successFul")
                        callback.onSuccess(stringData, response)
                    }

                    override fun onError(error: JsonObject?) {
                        TODO("Not yet implemented")
                    }

                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}