package beg.hr.kotlindesarrolladorandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import beg.hr.kotlindesarrolladorandroid.common.Navigator
import beg.hr.kotlindesarrolladorandroid.di.dagger2.ActivityComponent
import beg.hr.kotlindesarrolladorandroid.di.dagger2.ActivityModule
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsFragment
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsScreen

class MainActivity : AppCompatActivity(), Navigator {

    lateinit var component: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = (application as MyApplication).component
                .activityObjectGraphBuilder()
                .module(ActivityModule(this, this))
                .build()

        if (savedInstanceState == null) goTo(NewsScreen())
    }

    override fun goTo(key: Any) {
        when (key) {
            is NewsScreen -> supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, NewsFragment())
                    .commit()

            else -> throw IllegalStateException("Don't know how to handle key: $key")
        }
    }

    override fun goBack() {
        supportFragmentManager.popBackStack()
    }
}
