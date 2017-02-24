package beg.hr.kotlindesarrolladorandroid.news.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import beg.hr.kotlindesarrolladorandroid.MainActivity
import beg.hr.kotlindesarrolladorandroid.R
import beg.hr.kotlindesarrolladorandroid.common.hide
import beg.hr.kotlindesarrolladorandroid.common.show
import beg.hr.kotlindesarrolladorandroid.news.NewsModule
import kotlinx.android.synthetic.main.view_news.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment(), NewsScreen.View {

    @Inject lateinit var presenter: NewsScreen.NewsPresenter

    var state: NewsScreen.ViewState = defaultViewState()

    override fun render(state: NewsScreen.ViewState) {
        this.state = state
        if (state.loading) {
            progressBar.show()
            status.text = "Loading"
        } else {
            progressBar.hide()
            status.text = "Loaded"
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).component.newsBuilder().module(NewsModule(this)).build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.view_news, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            state = savedInstanceState.getParcelable("State")
        }
        render(state)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("State", state)
    }

    override fun onStart() {
        super.onStart()
        presenter.onLoad()
    }

    override fun onStop() {
        super.onStop()
        presenter.onDestroy()
    }
}
