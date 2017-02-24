package beg.hr.kotlindesarrolladorandroid

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import beg.hr.kotlindesarrolladorandroid.common.dagger2.ActivityComponent
import beg.hr.kotlindesarrolladorandroid.common.dagger2.ActivityModule
import beg.hr.kotlindesarrolladorandroid.common.ui.FlowViewState
import beg.hr.kotlindesarrolladorandroid.news.NewsModule
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsKey
import beg.hr.kotlindesarrolladorandroid.news.ui.NewsViewImpl
import flow.*

class MainActivity : AppCompatActivity(), KeyChanger {

    lateinit var component: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = (application as MyApplication).component
                .activityObjectGraphBuilder()
                .module(ActivityModule(this))
                .build()
    }

    override fun attachBaseContext(newBase: Context) {
        val baseContext = Flow.configure(newBase, this)
                .dispatcher(KeyDispatcher.configure(this, this).build())
                .defaultKey(NewsKey())
                .install()
        super.attachBaseContext(baseContext)
    }

    override fun onBackPressed() {
        if (!Flow.get(this).goBack())
            super.onBackPressed()
    }

    override fun changeKey(outgoingState: State?,
                           incomingState: State,
                           direction: Direction,
                           incomingContexts: MutableMap<Any, Context>,
                           callback: TraversalCallback) {

        val outKey: Any? = outgoingState?.getKey()
        val inKey: Any = incomingState.getKey()

        when (inKey) {
            is NewsKey -> {
                val newsComponent = component.newsBuilder()
                        .module(NewsModule(FlowViewState(incomingState)))
                        .build()
                val view = newsComponent.view()
                newsComponent.inject(view as NewsViewImpl)
                setContentView(view)
            }
            else -> throw IllegalStateException("Don't know how to handle this key")
        }

        callback.onTraversalCompleted()
    }

}
