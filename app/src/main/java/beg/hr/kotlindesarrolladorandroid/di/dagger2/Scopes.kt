package beg.hr.kotlindesarrolladorandroid.di.dagger2

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * Created by juraj on 24/02/2017.
 */

@Scope
@Retention(RUNTIME)
annotation class PerActivity

@Scope
@Retention(RUNTIME)
annotation class PerScreen
