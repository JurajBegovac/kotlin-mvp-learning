package beg.hr.kotlindesarrolladorandroid

import android.app.Application
import beg.hr.kotlindesarrolladorandroid.common.dagger2.AppComponent
import beg.hr.kotlindesarrolladorandroid.common.dagger2.AppModule
import beg.hr.kotlindesarrolladorandroid.common.dagger2.DaggerAppComponent
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsItem
import java.lang.System.currentTimeMillis
import java.util.concurrent.TimeUnit

/**
 * Created by juraj on 23/02/2017.
 */

class MyApplication : Application() {

    companion object {
        val dummyNews: List<NewsItem> = listOf(NewsItem(currentTimeMillis(), "Title 1", "Url 1"),
                NewsItem(currentTimeMillis() - TimeUnit.DAYS.toMillis(1), "Title 2", "Url 2"),
                NewsItem(currentTimeMillis() - TimeUnit.DAYS.toMillis(2), "Title 3", "Url 3"),
                NewsItem(currentTimeMillis() - TimeUnit.DAYS.toMillis(3), "Title 4", "Url 4"),
                NewsItem(currentTimeMillis() - TimeUnit.DAYS.toMillis(4), "Title 5", "Url 5"))
    }

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}
