package com.android.nytimes.adapters


import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.android.nytimes.R
import com.android.nytimes.activities.ArticleDetailsActivity
import com.android.nytimes.models.NewsArticle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.mikhaellopez.circularimageview.CircularImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.article_list_item.view.*

class NewsAdapter(internal var mCtx: Context, internal var newsArticleList: List<NewsArticle>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.article_list_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsArticleList[position]

        holder.ivArticle?.let {
            Glide.with(mCtx)
                .load(news.newsArticleMediaList!![0].newsArticleMediaMetaDataList!![0].url)
                .apply(
                        RequestOptions()
                                .error(R.drawable.imagenotfound)
                                .centerCrop()
                )
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        //on load failed
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        //on load success
                        return false
                    }
                })
                .into(it)
        }

        holder.tvArticleTitle?.text = news.title
        holder.tvArticleAuthor?.text = news.byline
        holder.tvArticleDate?.text = news.published_date
    }

    override fun getItemCount(): Int {
        return newsArticleList.size
    }

     inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ivArticle: ImageView?=null
        var tvArticleTitle: TextView?=null
        var tvArticleAuthor: TextView?=null
        var tvArticleDate: TextView?=null
        var cvArticleItem: CardView?=null


        init {

            findViews(itemView)
            setListeners()
        }

        private fun findViews(itemView: View) {

            ivArticle = itemView.ivArticle
            tvArticleTitle = itemView.tvArticleTitle
            tvArticleAuthor = itemView.tvArticleAuthor
            tvArticleDate = itemView.tvArticleDate
            cvArticleItem = itemView.cvArticleItem

        }

        private fun setListeners() {

            cvArticleItem?.setOnClickListener { openArticleDetails() }

        }

        private fun openArticleDetails() {
            val intent = Intent(mCtx, ArticleDetailsActivity::class.java)
            val newsArticle = newsArticleList[adapterPosition]
            intent.putExtra(NewsArticle.NEWS_ARTICLE_KEY, newsArticle)
            mCtx.startActivity(intent)
        }


    }
}