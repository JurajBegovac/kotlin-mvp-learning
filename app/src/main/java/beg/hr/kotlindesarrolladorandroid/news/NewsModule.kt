package beg.hr.kotlindesarrolladorandroid.news

import beg.hr.kotlindesarrolladorandroid.news.api.NewsManager
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsScreen
import beg.hr.kotlindesarrolladorandroid.news.ui.Presenter
import dagger.Module
import dagger.Provides

/**
 * Created by juraj on 24/02/2017.
 */
@Module
class NewsModule {

    @Provides
    fun presenter(newsManager: NewsManager): NewsScreen.NewsPresenter = Presenter(newsManager)
}