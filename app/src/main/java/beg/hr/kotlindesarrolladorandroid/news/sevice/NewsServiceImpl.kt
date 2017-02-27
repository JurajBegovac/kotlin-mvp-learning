package beg.hr.kotlindesarrolladorandroid.news.sevice

import beg.hr.kotlindesarrolladorandroid.news.api.NewsManager
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsItem
import rx.Observable
import rx.subjects.PublishSubject

/**
 * Created by juraj on 27/02/2017.
 */
class NewsServiceImpl(val newsManager: NewsManager) : NewsService {

    val newsSubject: PublishSubject<List<NewsItem>> = PublishSubject.create()

    override fun news(): Observable<List<NewsItem>> = newsSubject.asObservable()

    override fun command(newsCommand: NewsCommand) {
        when (newsCommand.type) {
            NewsCommand.TYPE_LOAD -> {
                newsManager.getNews().first().subscribe({ newsSubject.onNext(it) })
            }
            NewsCommand.TYPE_REFRESH -> {
                newsManager.getNews().first().subscribe({ newsSubject.onNext(it) })
            }
            else -> throw IllegalStateException("Don't know how to handle this command")
        }
    }
}