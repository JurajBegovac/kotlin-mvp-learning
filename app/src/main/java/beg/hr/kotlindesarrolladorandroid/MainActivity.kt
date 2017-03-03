package beg.hr.kotlindesarrolladorandroid

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import beg.hr.kotlindesarrolladorandroid.di.dagger2.ActivityComponent
import beg.hr.kotlindesarrolladorandroid.di.dagger2.ActivityModule
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsFragment
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsKey

class MainActivity : AppCompatActivity() {

    lateinit var component: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (application as MyApplication).component

        component = appComponent
                .activityObjectGraphBuilder()
                .module(ActivityModule(this))
                .build()

        if (savedInstanceState == null) goTo(NewsKey())
    }

    private fun goTo(it: Parcelable) {
        when (it) {
            is NewsKey -> supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, NewsFragment())
                    .commit()
        }
    }
}
