package com.example.phonepemoviesapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MainUtil {

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        } else {
            try {
                ConnectivityManager ConnectMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (ConnectMgr == null) {
                    return false;
                } else {
                    NetworkInfo NetInfo = ConnectMgr.getActiveNetworkInfo();
                    return NetInfo != null && NetInfo.isConnected();
                }
            } catch (SecurityException var3) {
                var3.printStackTrace();
                return false;
            }
        }
    }
}
