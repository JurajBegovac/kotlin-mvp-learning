package beg.hr.kotlindesarrolladorandroid.di.dagger2

import android.app.Activity
import android.content.Context
import beg.hr.kotlindesarrolladorandroid.common.Navigator
import beg.hr.kotlindesarrolladorandroid.news.NewsComponent
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Created by juraj on 24/02/2017.
 */

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun context(): Context
    fun newsBuilder(): NewsComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun module(module: ActivityModule): Builder
        fun build(): ActivityComponent
    }
}

@Module(subcomponents = arrayOf(NewsComponent::class))
class ActivityModule(val activity: Activity, val navigator: Navigator) {

    @Provides
    @PerActivity
    fun activity() = activity

    @Provides
    @PerActivity
    fun context(): Context = activity

    @Provides
    @PerActivity
    fun dispatcher() = navigator
}
