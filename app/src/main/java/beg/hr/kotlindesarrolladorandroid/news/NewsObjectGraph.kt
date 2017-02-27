package beg.hr.kotlindesarrolladorandroid.news

import android.content.Context
import android.view.View
import beg.hr.kotlindesarrolladorandroid.R
import beg.hr.kotlindesarrolladorandroid.common.dagger2.ActivityContext
import beg.hr.kotlindesarrolladorandroid.common.dagger2.PerScreen
import beg.hr.kotlindesarrolladorandroid.common.ui.ViewState
import beg.hr.kotlindesarrolladorandroid.news.api.NewsManager
import beg.hr.kotlindesarrolladorandroid.news.api.NewsManagerImpl
import beg.hr.kotlindesarrolladorandroid.news.sevice.NewsService
import beg.hr.kotlindesarrolladorandroid.news.sevice.NewsServiceImpl
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsPresenter
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsPresenterImpl
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsView
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsViewImpl
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Created by juraj on 24/02/2017.
 */
@PerScreen
@Subcomponent(modules = arrayOf(NewsModule::class))
interface NewsComponent {
    fun inject(target: NewsViewImpl)
    fun view(): NewsView

    @Subcomponent.Builder
    interface Builder {
        fun module(module: NewsModule): Builder
        fun build(): NewsComponent
    }
}

@Module
class NewsModule(val viewState: ViewState) {

    @Provides
    @PerScreen
    fun newsManager(): NewsManager = NewsManagerImpl()

    @Provides
    @PerScreen
    fun newsService(newsManager: NewsManager): NewsService = NewsServiceImpl(newsManager)

    @Provides
    @PerScreen
    fun presenter(newsService: NewsService): NewsPresenter = NewsPresenterImpl(viewState, newsService)

    @Provides
    @PerScreen
    fun view(@ActivityContext context: Context): NewsView = View.inflate(context, R.layout.view_news, null) as NewsView
}
