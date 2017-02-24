package beg.hr.kotlindesarrolladorandroid.news.ui

import beg.hr.kotlindesarrolladorandroid.common.Navigator
import beg.hr.kotlindesarrolladorandroid.news.api.NewsManager
import rx.Observable
import rx.android.schedulers.AndroidSchedulers.mainThread
import java.util.concurrent.TimeUnit

/**
 * Created by juraj on 23/02/2017.
 */
class Presenter constructor(val view: NewsScreen.View, val newsManager: NewsManager, val navigator: Navigator) : NewsScreen.NewsPresenter {

    override fun onLoad() {
        Observable.just(newsManager.getNews())
                .delay(1000, TimeUnit.MILLISECONDS)
                .observeOn(mainThread())
                .subscribe {
                    news ->
                    view.render(NewsScreen.ViewState(false, news))
                }
    }

    override fun onDestroy() {
        // unsubscribe
    }
}