package com.android.nytimes.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class NewsArticle : Serializable {

    @SerializedName("url")
    private val url: String? = null
    @SerializedName("byline")
    val byline: String? = null
    @SerializedName("title")
    val title: String? = null
    @SerializedName("published_date")
    val published_date: String? = null
    @SerializedName("abstract")
    val abstractarticle: String? = null

    @SerializedName("media")
    @Expose
    val newsArticleMediaList: List<NewsArticleMedia>? = null

    companion object {

        val NEWS_ARTICLE_KEY = "NEWS_ARTICLE_KEY"
    }

}