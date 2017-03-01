package beg.hr.kotlindesarrolladorandroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, create())
                    .commit()
        }
    }

    private fun create(): Fragment {
        return NewsFragment()
    }
}
