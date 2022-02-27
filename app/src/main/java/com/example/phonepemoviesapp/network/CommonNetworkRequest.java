package com.example.phonepemoviesapp.network;

import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CommonNetworkRequest extends StringRequest {

    private static final String TAG = "Volley";

    private final HashMap<String, String> mHeaders;
    private final String mBody;
    private Response.Listener<String> mResponseListener;
    @Nullable
    private Response.ErrorListener mErrorListener;

    CommonNetworkRequest(int method, String url, HashMap<String, String> headers, String body,
                         Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.mHeaders = headers;
        this.mBody = body;
        mResponseListener = listener;
        mErrorListener = errorListener;

    }

    @Override
    protected void deliverResponse(String response) {
        printResponse(response);
        mResponseListener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        printError(error != null ? error.getMessage() : "Unknown");
        if (mErrorListener != null) {
            mErrorListener.onErrorResponse(error);
        }
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (mBody != null) {
            return mBody.getBytes();
        }
        return super.getBody();
    }

    private String getMethodString(int method){
        switch (method) {
            case Method.GET:
                return "GET";
            case Method.POST:
                return "POST";
                default:
            return "UNKNOWN";
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        if (mHeaders != null) {
            return mHeaders;
        }
        return super.getHeaders();
    }

    private synchronized void printResponse(String response){
        StringBuilder builder = new StringBuilder();
        builder.append("URL: " + getUrl() + "\n")
                .append("METHOD: " + getMethodString(getMethod()) + "\n")
                .append("Request Header: " + (mHeaders != null ? mHeaders : "") + "\n")
                .append("Request Body: " + (mBody != null ? mBody : "") + "\n")
                .append("Response Body: " + response);

        Log.d(TAG,builder.toString());
    }
    private synchronized void printError(String error){
        StringBuilder builder = new StringBuilder();
        builder.append("URL: " + getUrl() + "\n")
                .append("METHOD: " + getMethodString(getMethod()) + "\n")
                .append("Request Header: " + (mHeaders != null ? mHeaders : "") + "\n")
                .append("Request Body: " + (mBody != null ? mBody : "") + "\n")
                .append("Error: " + error);

        Log.d(TAG,builder.toString());
    }
}
