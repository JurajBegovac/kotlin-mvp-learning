package beg.hr.kotlindesarrolladorandroid.di.dagger2

import android.app.Application
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

}
