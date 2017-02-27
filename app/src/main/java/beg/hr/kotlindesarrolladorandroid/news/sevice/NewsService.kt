package beg.hr.kotlindesarrolladorandroid.news.sevice

import beg.hr.kotlindesarrolladorandroid.news.ui.NewsItem
import rx.Observable

/**
 * Created by juraj on 27/02/2017.
 */
interface NewsService {
    fun news(): Observable<List<NewsItem>>

    fun command(newsCommand: NewsCommand)
}