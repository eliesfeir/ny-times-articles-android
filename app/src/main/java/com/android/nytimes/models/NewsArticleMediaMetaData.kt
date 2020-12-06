package com.android.nytimes.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class NewsArticleMediaMetaData : Serializable {
    @SerializedName("url")
    val url: String? = null
}
