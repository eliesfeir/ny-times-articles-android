package com.android.nytimes.activities

import android.os.Bundle
import android.widget.Toast

import com.android.nytimes.R
import com.android.nytimes.models.NewsArticle
import com.bumptech.glide.Glide

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_article_details.*

class ArticleDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        getIntentExtras()

    }

    private fun getIntentExtras() {

        val newsArticle = intent.getSerializableExtra(NewsArticle.NEWS_ARTICLE_KEY) as NewsArticle
        if (newsArticle != null) {

            tvArticleDetailTitle.text = newsArticle.title
            tvArticleDetailAbstract.text = newsArticle.abstractarticle
            tvArticleDetailDate.text = newsArticle.published_date

            Glide.with(this)
                    .load(newsArticle.newsArticleMediaList!![0].newsArticleMediaMetaDataList!![0].url)
                    .into(ivArticleDetail)
        } else {

            Toast.makeText(this, R.string.could_not_load_article, Toast.LENGTH_LONG).show()
        }
    }


}
