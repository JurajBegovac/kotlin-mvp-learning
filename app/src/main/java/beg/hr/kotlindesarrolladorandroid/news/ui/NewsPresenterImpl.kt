package beg.hr.kotlindesarrolladorandroid.news.ui

import android.os.Bundle
import beg.hr.kotlindesarrolladorandroid.common.ui.UserActionEvent
import beg.hr.kotlindesarrolladorandroid.common.ui.ViewState
import beg.hr.kotlindesarrolladorandroid.news.api.NewsManager
import rx.Observable
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.subscriptions.CompositeSubscription
import java.util.concurrent.TimeUnit

/**
 * Created by juraj on 23/02/2017.
 */
class NewsPresenterImpl constructor(val viewState: ViewState, val newsManager: NewsManager) : NewsPresenter() {

    lateinit var state: State

    var subscription: CompositeSubscription = CompositeSubscription()

    override fun onLoad() {
        val storedState: State = viewState.get()?.getParcelable(State.KEY) ?: State.DEFAULT_STATE

        val storedStateObservable = Observable.just(storedState)

        val newStateObservable = Observable.just(newsManager.getNews())
                .delay(1000, TimeUnit.MILLISECONDS)
                .map { State(false, it) }

        subscription.add(view?.userActions()?.subscribe { action -> handleAction(action) })

        subscription.add(Observable.merge(storedStateObservable, newStateObservable)
                .distinctUntilChanged()
                .observeOn(mainThread())
                .subscribe {
                    state1 ->
                    state = state1
                    view?.render(state1)
                })
    }

    private fun handleAction(action: UserActionEvent) {
        when (action.type) {
            ActionTypes.BUTTON_CLICKED -> {
                view?.render(State(true, emptyList()))
            }
            else -> throw IllegalStateException("Don't know how to handle this type")
        }
    }

    override fun onDestroy() {
        viewState.set(bundleToSave())
        subscription.clear()
    }

    private fun bundleToSave(): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(State.KEY, state)
        return bundle
    }
}