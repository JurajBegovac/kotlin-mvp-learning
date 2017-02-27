package beg.hr.kotlindesarrolladorandroid.common.ui

import rx.Observable

/**
 * Created by juraj on 27/02/2017.
 */
interface View<S> {
    fun render(state: S)

    fun userActions(): Observable<UserActionEvent>
}