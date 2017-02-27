package beg.hr.kotlindesarrolladorandroid.news

import android.util.Log
import beg.hr.kotlindesarrolladorandroid.MyApplication
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsItem
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by juraj on 27/02/2017.
 */
class NewsInteractor {

    fun getNews(): Observable<List<NewsItem>> {
        return Observable.just(MyApplication.dummyNews)
                .delay(1000, TimeUnit.MILLISECONDS)
                .doOnNext({ Log.i("TAG", it.toString()) })
    }
}