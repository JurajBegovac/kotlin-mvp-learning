package beg.hr.kotlindesarrolladorandroid

import android.content.Context
import android.support.v7.app.AppCompatActivity
import beg.hr.kotlindesarrolladorandroid.common.FragmentDispatcher
import beg.hr.kotlindesarrolladorandroid.common.FragmentFactoryImpl
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsScreen
import flow.Flow

class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val newBase2 = Flow.configure(newBase, this)
                .dispatcher(FragmentDispatcher(supportFragmentManager, android.R.id.content, FragmentFactoryImpl()))
                .defaultKey(NewsScreen())
                .install()
        super.attachBaseContext(newBase2)
    }
}
