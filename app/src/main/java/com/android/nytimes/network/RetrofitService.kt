package com.android.nytimes.network


import com.android.nytimes.utils.APIVars

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val retrofit = Retrofit.Builder()
            .baseUrl(APIVars.NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun <S> cteateService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

}