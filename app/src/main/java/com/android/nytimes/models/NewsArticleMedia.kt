package com.android.nytimes.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class NewsArticleMedia : Serializable {

    @SerializedName("media-metadata")
    @Expose
    val newsArticleMediaMetaDataList: List<NewsArticleMediaMetaData>? = null


}
