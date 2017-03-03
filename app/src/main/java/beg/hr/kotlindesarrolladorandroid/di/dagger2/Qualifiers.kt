package beg.hr.kotlindesarrolladorandroid.di.dagger2

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * Created by juraj on 02/03/2017.
 */
@Qualifier
@MustBeDocumented
@Retention(RUNTIME)
annotation class ApplicationContext


@Qualifier
@MustBeDocumented
@Retention(RUNTIME)
annotation class ActivityContext
