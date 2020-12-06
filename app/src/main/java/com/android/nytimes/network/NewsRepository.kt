package com.android.nytimes.network

import com.android.nytimes.models.NewsResponse

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {
    val newsApi: NewsApi

    val news: MutableLiveData<NewsResponse>
        get() {
            val newsData = MutableLiveData<NewsResponse>()
            newsApi.newsList.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>,
                                        response: Response<NewsResponse>) {
                    if (response.isSuccessful) {
                        newsData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    newsData.value = null
                }
            })
            return newsData
        }

    init {
        newsApi = RetrofitService.cteateService(NewsApi::class.java)
    }

    companion object {

        private var newsRepository: NewsRepository? = null

        val instance: NewsRepository
            get() {
                if (newsRepository == null) {
                    newsRepository = NewsRepository()
                }
                return newsRepository as NewsRepository
            }
    }
}