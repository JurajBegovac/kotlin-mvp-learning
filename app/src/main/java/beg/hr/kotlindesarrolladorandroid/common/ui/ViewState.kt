package beg.hr.kotlindesarrolladorandroid.common.ui

import android.os.Bundle

/**
 * Created by juraj on 24/02/2017.
 */
interface ViewState {
    fun get(): Bundle?

    fun set(bundle: Bundle)
}