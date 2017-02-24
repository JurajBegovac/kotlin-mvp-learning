package beg.hr.kotlindesarrolladorandroid.news.api

import beg.hr.kotlindesarrolladorandroid.news.ui.NewsItem

/**
 * Created by juraj on 24/02/2017.
 */
class NewsManager {

    fun getNews(): List<NewsItem> {
        return listOf(NewsItem("Title 1", "url 1"), NewsItem("Title 2", "url 2"))
    }
}