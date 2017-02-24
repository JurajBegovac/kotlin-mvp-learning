package beg.hr.kotlindesarrolladorandroid

import android.app.Application
import beg.hr.kotlindesarrolladorandroid.common.dagger2.AppComponent
import beg.hr.kotlindesarrolladorandroid.common.dagger2.AppModule
import beg.hr.kotlindesarrolladorandroid.common.dagger2.DaggerAppComponent

/**
 * Created by juraj on 23/02/2017.
 */

class MyApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}
