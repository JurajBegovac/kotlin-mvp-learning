package beg.hr.kotlindesarrolladorandroid.network

import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by juraj on 01/03/2017.
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun baseUrl(): HttpUrl {
        return HttpUrl.parse("https://www.reddit.com")
    }

    @Provides
    fun converterFactory(): Converter.Factory {
        return MoshiConverterFactory.create()
    }

    @Provides
    @Singleton
    fun retrofit(httpUrl: HttpUrl, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(httpUrl)
                .addConverterFactory(converterFactory)
                .build()
    }
}
