package beg.hr.kotlindesarrolladorandroid.common.ui

import android.os.Bundle
import flow.State

/**
 * Created by juraj on 24/02/2017.
 */
class FlowViewState(val state: State) : ViewState {
    override fun get(): Bundle? = state.bundle

    override fun set(bundle: Bundle) {
        state.bundle = bundle
    }
}