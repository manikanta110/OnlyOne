package com.example.user.onlyone;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by user on 20-Jan-18.
 */

public class Common {


    public boolean CheckNetworkConnextion(Context context) {


        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {
            return  true;

        }

        return false;
    }



}
