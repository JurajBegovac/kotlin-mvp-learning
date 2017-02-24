package beg.hr.kotlindesarrolladorandroid.news

import beg.hr.kotlindesarrolladorandroid.common.Navigator
import beg.hr.kotlindesarrolladorandroid.di.dagger2.PerScreen
import beg.hr.kotlindesarrolladorandroid.news.api.NewsManager
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsFragment
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsScreen
import beg.hr.kotlindesarrolladorandroid.news.ui.Presenter
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Created by juraj on 24/02/2017.
 */
@PerScreen
@Subcomponent(modules = arrayOf(NewsModule::class))
interface NewsComponent {
    fun inject(target: NewsFragment)

    @Subcomponent.Builder
    interface Builder {
        fun module(module: NewsModule): Builder
        fun build(): NewsComponent
    }
}

@Module
class NewsModule(val view: NewsScreen.View) {

    @Provides
    @PerScreen
    fun newsManager() = NewsManager()

    @Provides
    @PerScreen
    fun presenter(newsManager: NewsManager, navigator: Navigator): NewsScreen.NewsPresenter = Presenter(view, newsManager, navigator)
}
