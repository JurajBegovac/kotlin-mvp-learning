package beg.hr.kotlindesarrolladorandroid.common

import android.support.v4.app.Fragment
import beg.hr.kotlindesarrolladorandroid.news.DaggerNewsComponent
import beg.hr.kotlindesarrolladorandroid.news.NewsModule
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsFragment
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsScreen

/**
 * Created by juraj on 24/02/2017.
 */

interface FragmentFactory {
    fun create(key: Any): Fragment
}

class FragmentFactoryImpl : FragmentFactory {
    override fun create(key: Any): Fragment {
        when (key) {
            is NewsScreen -> {
                val newsFragment = NewsFragment()
                DaggerNewsComponent.builder().newsModule(NewsModule()).build().inject(newsFragment)
                return newsFragment
            }
            else -> throw IllegalStateException("Key is not supported")
        }
    }
}
