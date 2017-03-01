package beg.hr.kotlindesarrolladorandroid.news.api

import beg.hr.kotlindesarrolladorandroid.news.ui.NewsItem
import rx.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by juraj on 24/02/2017.
 */
class NewsManager @Inject constructor() {

    fun getNews(): Observable<List<NewsItem>> {
        return Observable.just(listOf(NewsItem("Title 1", "url 1"), NewsItem("Title 2", "url 2")))
                .delay(1, TimeUnit.SECONDS)
    }
}