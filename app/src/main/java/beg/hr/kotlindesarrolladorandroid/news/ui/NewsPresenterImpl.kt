package beg.hr.kotlindesarrolladorandroid.news.ui

import android.os.Bundle
import beg.hr.kotlindesarrolladorandroid.common.ui.UserActionEvent
import beg.hr.kotlindesarrolladorandroid.common.ui.ViewState
import beg.hr.kotlindesarrolladorandroid.news.sevice.NewsCommand
import beg.hr.kotlindesarrolladorandroid.news.sevice.NewsService
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.subscriptions.CompositeSubscription

/**
 * Created by juraj on 23/02/2017.
 */
class NewsPresenterImpl constructor(val viewState: ViewState, val newsService: NewsService) : NewsPresenter() {

    lateinit var state: State
    var subscription: CompositeSubscription = CompositeSubscription()

    override fun onLoad() {
        subscribeToNews()
        newsService.command(NewsCommand(NewsCommand.TYPE_LOAD))
    }

    private fun subscribeToNews() {
        // get stored state
        val storedState: State = viewState.get()?.getParcelable(State.KEY) ?: State.DEFAULT_STATE

        // observe news
        val newStateObservable = newsService.news()
                .map { State(false, it) }

        val s1 = view?.userActions()?.subscribe { action -> handleAction(action) }

        val s2 = newStateObservable
                .startWith(storedState)
                .observeOn(mainThread())
                .subscribe { state1 ->
                    state = state1
                    view?.render(state1)
                }
        subscription.addAll(s1, s2)
    }

    override fun onDestroy() {
        viewState.set(bundleToSave())
        subscription.clear()
    }

    private fun handleAction(action: UserActionEvent) {
        when (action.type) {
            ActionTypes.REFRESH -> {
                state = state.copy(loading = true)
                view?.render(state)
                newsService.command(NewsCommand(NewsCommand.TYPE_REFRESH))
            }
            else -> throw IllegalStateException("Don't know how to handle this type")
        }
    }

    private fun bundleToSave(): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(State.KEY, state)
        return bundle
    }
}