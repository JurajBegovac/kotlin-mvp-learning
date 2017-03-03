package beg.hr.kotlindesarrolladorandroid.di.dagger2

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Created by juraj on 02/03/2017.
 */

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun module(module: ActivityModule): Builder
        fun build(): ActivityComponent
    }
}

@Module // todo (subcomponents = arrayOf(NewsComponent::class))
class ActivityModule(val activity: Activity) {

    @Provides
    @PerActivity
    fun activity() = activity

    @Provides
    @PerActivity
    @ActivityContext
    fun context(): Context = activity
}
