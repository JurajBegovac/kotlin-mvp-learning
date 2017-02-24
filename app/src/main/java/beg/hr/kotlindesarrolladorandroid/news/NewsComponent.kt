package beg.hr.kotlindesarrolladorandroid.news

import beg.hr.kotlindesarrolladorandroid.news.ui.NewsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by juraj on 24/02/2017.
 */
@Singleton
@Component(modules = arrayOf(NewsModule::class))
interface NewsComponent {
    fun inject(target: NewsFragment)
}