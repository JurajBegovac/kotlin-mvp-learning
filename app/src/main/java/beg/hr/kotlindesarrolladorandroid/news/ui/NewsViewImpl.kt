package beg.hr.kotlindesarrolladorandroid.news.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_news.view.*
import javax.inject.Inject

/**
 * Created by juraj on 24/02/2017.
 */
class NewsViewImpl @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr), NewsView {

    @Inject lateinit var presenter: NewsPresenter

    override fun render(state: State) {
        if (state.loading) {
            progressBar.visibility = View.VISIBLE
            status.text = "Loading"
        } else {
            progressBar.visibility = View.GONE
            status.text = "Loaded"
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.takeView(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dropView(this)
    }
}
