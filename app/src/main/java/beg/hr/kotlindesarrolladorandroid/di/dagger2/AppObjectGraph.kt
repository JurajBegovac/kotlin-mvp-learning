package beg.hr.kotlindesarrolladorandroid.di.dagger2

import android.app.Application
import android.content.Context
import beg.hr.kotlindesarrolladorandroid.Navigation
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by juraj on 02/03/2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun application(): Application
    fun activityObjectGraphBuilder(): ActivityComponent.Builder
    fun navigation(): Navigation
}

@Module(subcomponents = arrayOf(ActivityComponent::class))
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun application() = application

    @Provides
    @Singleton
    @ApplicationContext
    fun context(): Context = application.applicationContext
}