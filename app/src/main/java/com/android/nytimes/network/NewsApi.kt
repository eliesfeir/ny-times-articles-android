package com.android.nytimes.network

import com.android.nytimes.models.NewsResponse
import com.android.nytimes.utils.APIVars
import com.android.nytimes.utils.APIVars.NEWS_API_END_POINT

import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {

    @get:GET(NEWS_API_END_POINT)
    val newsList: Call<NewsResponse>

}
