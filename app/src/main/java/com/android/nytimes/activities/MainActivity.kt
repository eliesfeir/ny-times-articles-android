package com.android.nytimes.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Toast

import com.android.nytimes.R
import com.android.nytimes.adapters.NewsAdapter
import com.android.nytimes.models.NewsArticle
import com.android.nytimes.models.NewsResponse
import com.android.nytimes.utils.NetworkUtils
import com.android.nytimes.viewmodels.NewsViewModel

import java.util.ArrayList

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
     var articleArrayList = ArrayList<NewsArticle>()
     var newsAdapter: NewsAdapter? = null
     var newsViewModel: NewsViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()

        setListeners()

        getNewsArticles()

    }


    private fun setListeners() {
        srlArticles.setOnRefreshListener { getNewsArticles() }
    }

    private fun initialize() {
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel?.init()
    }

    private fun getNewsArticles() {

        if (NetworkUtils.isConnectionAvailable(this)) {
            srlArticles.isRefreshing = true
            newsViewModel?.requestNews()
            newsViewModel?.mutableLiveData?.observe(this, Observer { newsResponse ->
                try {
                    val newsArticles = newsResponse.newsList
                    articleArrayList.addAll(newsArticles!!)
                    newsAdapter!!.notifyDataSetChanged()
                } catch (ex: Exception) {
                    Toast.makeText(this, R.string.something_wrong, Toast.LENGTH_LONG).show()
                } finally {
                    srlArticles.isRefreshing = false
                }
            })

            setupRecyclerView()
        } else {
            Toast.makeText(this, R.string.check_internet_connection, Toast.LENGTH_LONG).show()
            srlArticles.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(this, articleArrayList)
            rvArticles.adapter = newsAdapter
        } else {
            newsAdapter!!.notifyDataSetChanged()
        }
    }
}
