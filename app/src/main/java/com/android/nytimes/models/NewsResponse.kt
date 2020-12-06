package com.android.nytimes.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsResponse {

    @SerializedName("status")
    @Expose
    private val status: String? = null
    @SerializedName("num_results")
    @Expose
    private val num_results: Int? = null
    @SerializedName("results")
    @Expose
    val newsList: List<NewsArticle>? = null

}