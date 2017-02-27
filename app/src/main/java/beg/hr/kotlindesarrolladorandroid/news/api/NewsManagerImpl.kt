package beg.hr.kotlindesarrolladorandroid.news.api

import android.util.Log
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsItem
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by juraj on 24/02/2017.
 */
class NewsManagerImpl : NewsManager {
    override fun getNews(): Observable<List<NewsItem>> {
        return Observable.just(listOf(NewsItem("Title 1", "url 1"), NewsItem("Title 2", "url 2")))
                .delay(1000, TimeUnit.MILLISECONDS)
                .doOnNext({ Log.i("TAG", it.toString()) })
    }
}