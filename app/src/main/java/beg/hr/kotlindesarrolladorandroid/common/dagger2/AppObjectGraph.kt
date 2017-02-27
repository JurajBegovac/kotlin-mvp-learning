package beg.hr.kotlindesarrolladorandroid.common.dagger2

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by juraj on 24/02/2017.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun application(): Application
    fun activityObjectGraphBuilder(): ActivityComponent.Builder
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
