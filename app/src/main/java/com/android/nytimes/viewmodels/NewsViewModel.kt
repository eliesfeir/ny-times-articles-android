package com.android.nytimes.viewmodels

import com.android.nytimes.models.NewsResponse
import com.android.nytimes.network.NewsRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {

     var mutableLiveData: MutableLiveData<NewsResponse>? = null
     var newsRepository: NewsRepository? = null

    fun init() {
        if (mutableLiveData != null) {
            return
        }
        newsRepository = NewsRepository.instance

    }

    fun requestNews() {
        mutableLiveData = newsRepository!!.news
    }

    fun getNewsRepository(): LiveData<NewsResponse>? {
        return mutableLiveData
    }

}