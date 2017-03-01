package beg.hr.kotlindesarrolladorandroid.news.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import beg.hr.kotlindesarrolladorandroid.R
import beg.hr.kotlindesarrolladorandroid.news.DaggerNewsComponent
import beg.hr.kotlindesarrolladorandroid.news.api.NewsManager
import kotlinx.android.synthetic.main.view_news.*
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment() {

    @Inject lateinit var newsManager: NewsManager

    val subscription: CompositeSubscription = CompositeSubscription()

    lateinit var currentViewState: ViewState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerNewsComponent.create().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.view_news, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentViewState = savedInstanceState?.getParcelable(ViewState.KEY) ?: ViewState.DEFAULT

        subscription.add(newsManager.getNews().map { ViewState(false, it) }
                .startWith(currentViewState)
                .observeOn(mainThread())
                .subscribe { render(it) })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscription.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(ViewState.KEY, currentViewState)
    }

    fun render(state: ViewState) {
        currentViewState = state
        if (state.loading) {
            progressBar.visibility = View.VISIBLE
            status.text = "Loading"
        } else {
            progressBar.visibility = View.GONE
            status.text = "Loaded"
        }
    }
}
