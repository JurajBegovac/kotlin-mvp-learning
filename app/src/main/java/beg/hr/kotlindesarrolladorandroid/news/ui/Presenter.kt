package beg.hr.kotlindesarrolladorandroid.news.ui

import beg.hr.kotlindesarrolladorandroid.common.ViewPresenter
import beg.hr.kotlindesarrolladorandroid.news.api.NewsManager
import rx.Observable
import rx.android.schedulers.AndroidSchedulers.mainThread
import java.util.concurrent.TimeUnit

/**
 * Created by juraj on 23/02/2017.
 */
class Presenter constructor(val newsManager: NewsManager) : ViewPresenter<NewsScreen.View>(), NewsScreen.NewsPresenter {

    override fun onLoad() {
        super.onLoad()
        view?.render(NewsScreen.ViewState(true, emptyList()))

        Observable.just(newsManager.getNews())
                .delay(1000, TimeUnit.MILLISECONDS)
                .observeOn(mainThread())
                .subscribe {
                    news ->
                    view?.render(NewsScreen.ViewState(false, news))
                }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}