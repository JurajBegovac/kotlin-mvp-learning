package beg.hr.kotlindesarrolladorandroid.news.api

import beg.hr.kotlindesarrolladorandroid.news.ui.NewsScreen

/**
 * Created by juraj on 24/02/2017.
 */
class NewsManager {

    fun getNews(): List<NewsScreen.NewsItem> {
        return listOf(NewsScreen.NewsItem("Title 1", "url 1"), NewsScreen.NewsItem("Title 2", "url 2"))
    }
}