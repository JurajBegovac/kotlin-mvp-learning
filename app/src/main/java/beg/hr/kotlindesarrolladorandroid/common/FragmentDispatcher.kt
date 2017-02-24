package beg.hr.kotlindesarrolladorandroid.common

import android.support.v4.app.FragmentManager
import flow.Dispatcher
import flow.State
import flow.Traversal
import flow.TraversalCallback

/**
 * Created by juraj on 23/02/2017.
 */
class FragmentDispatcher(val fragmentManager: FragmentManager, val rootId: Int, val fragmentFactory: FragmentFactory) : Dispatcher {
    override fun dispatch(traversal: Traversal, callback: TraversalCallback) {
        val outState: State? = if (traversal.origin != null) traversal.getState(traversal.origin!!.top()) else null
        val outKey: Any? = outState?.getKey()

        val inState = traversal.getState(traversal.destination.top())
        val inKey: Any = inState.getKey()

        if (inKey == outKey) {
            callback.onTraversalCompleted()
            return
        }

        fragmentManager.beginTransaction()
                .replace(rootId, fragmentFactory.create(inKey))
                .commit()
    }
}