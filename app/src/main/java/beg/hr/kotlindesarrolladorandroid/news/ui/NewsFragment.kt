package beg.hr.kotlindesarrolladorandroid.news.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import beg.hr.kotlindesarrolladorandroid.R
import kotlinx.android.synthetic.main.view_news.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment(), NewsScreen.View {

    @Inject lateinit var presenter: NewsScreen.NewsPresenter

    override fun render(state: NewsScreen.ViewState) {
        if (state.loading) {
            progressBar.visibility = View.VISIBLE
            status.text = "Loading"
        } else {
            progressBar.visibility = View.GONE
            status.text = "Loaded"
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.view_news, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.takeView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.dropView(this)
    }
}
