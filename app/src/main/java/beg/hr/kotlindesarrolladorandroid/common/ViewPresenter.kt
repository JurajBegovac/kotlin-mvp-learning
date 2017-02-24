package beg.hr.kotlindesarrolladorandroid.common

/** Created by juraj on 24/02/2017.  */
abstract class ViewPresenter<V> : Presenter<V> {

    /**
     * Returns the view managed by this presenter, or null if [.takeView] has never been called,
     * or after [.dropView].
     */
    protected var view: V? = null
        private set

    /** Load has been called for the current [.view].  */
    private var loaded: Boolean = false

    /**
     * Called to give this presenter control of a view, typically from [ ][android.view.View.onAttachedToWindow]. Sets the view that will be returned from [ ][.getView].

     *
     * This presenter will be immediately [registered][BundleService.register] (or
     * re-registered) with the given view's scope, leading to an immediate call to [.onLoad].

     *
     * It is expected that [.dropView] will be called with the same argument when the
     * view is no longer active, e.g. from [android.view.View.onDetachedFromWindow].

     * @see BundleService.register
     */
    override fun takeView(view: V) {
        if (view == null) throw NullPointerException("new view must not be null")

        if (this.view !== view) {
            if (this.view != null) dropView(this.view as V)

            this.view = view
            onLoad()
        }
    }

    /**
     * Called to surrender control of this view, e.g. when the view is detached. If and only if the
     * given view matches the last passed to [.takeView], the reference to the view is cleared.

     *
     * Mismatched views are a no-op, not an error. This is to provide protection in the not
     * uncommon case that dropView and takeView are called out of order. For example, an activity's
     * views are typically inflated in [android.app.Activity.onCreate], but are only detached
     * some time after [onExitScope][android.app.Activity.onDestroy]. It's possible for a view
     * from one activity to be detached well after the window for the next activity has its views
     * inflatedthat is, after the next activity's onResume call.
     */
    override fun dropView(view: V) {
        if (view == null) throw NullPointerException("dropped view must not be null")
        if (view === this.view) {
            loaded = false
            onDestroy()
            this.view = null
        }
    }

    /**
     * @return true if this presenter is currently managing a view, or false if [.takeView] has
     * *     never been called, or after [.dropView].
     */
    protected fun hasView(): Boolean {
        return view != null
    }

    /**
     * Like [Bundler.onLoad], but called only when [.getView] is not null, and
     * debounced. That is, this method will be called exactly once for a given view instance, at least
     * until that view is [dropped][.dropView].

     *
     * See [.takeView] for details.
     */
    protected open fun onLoad() {
    }

    /** Like [Bundler.onDestroy].  */
    protected open fun onDestroy() {
    }
}
