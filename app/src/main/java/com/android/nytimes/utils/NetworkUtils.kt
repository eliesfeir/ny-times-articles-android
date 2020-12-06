package com.android.nytimes.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    fun isConnectionAvailable(context: Context): Boolean {
        try {
            val connec = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            val mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (wifi!!.isConnected || mobile!!.isConnected)
                return true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }
}
