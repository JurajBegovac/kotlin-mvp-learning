package beg.hr.kotlindesarrolladorandroid.news.api

import beg.hr.kotlindesarrolladorandroid.news.ui.NewsItem
import rx.Observable

/**
 * Created by juraj on 27/02/2017.
 */
interface NewsManager {
    fun getNews(): Observable<List<NewsItem>>
}