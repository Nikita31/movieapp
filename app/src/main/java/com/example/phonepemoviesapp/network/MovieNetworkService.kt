package com.example.phonepemoviesapp.network

import android.content.Context
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import java.util.HashMap

class MovieNetworkService {

    interface IResponseListener {
        fun onSuccess(stringData: String?, response: JsonObject?)
        fun onError(error: JsonObject?)
    }

    interface Callback<T> {
        // Success event delegate. It will received GSON deserialized object response
        fun onSuccess()

        // Failure event delegate. We need to pass more details here.
        fun onError()
    }

    interface Method {
        companion object {
            const val DEPRECATED_GET_OR_POST = -1
            const val GET = 0
            const val POST = 1
            const val PUT = 2
            const val DELETE = 3
            const val HEAD = 4
            const val OPTIONS = 5
            const val TRACE = 6
            const val PATCH = 7
        }
    }

    fun fetch(
        context: Context?,
        url: String?,
        method: Int,
        body: String?,
        headers: HashMap<String?, String?>?,
        responseListener: IResponseListener
    ) {
        val networkRequest = CommonNetworkRequest(
            method,
            url,
            headers,
            body,
            { response -> responseListener.onSuccess(response, JsonObject()) }) {
            val errorObject = JsonObject()
            errorObject.addProperty("error", "failed")
            responseListener.onError(errorObject)
        }
        Volley.newRequestQueue(context).add(networkRequest)
    }

}