package ru.kanogor.hammersystems.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.kanogor.hammersystems.data.Repository
import ru.kanogor.hammersystems.data.SearchApiInfo

private const val BASE_URL = "https://pizzaallapala.p.rapidapi.com/"
private const val SHARED_PREFERENCE_NAME = "preference_db_name"

val dataModule = module {

    single {
        Repository(searchApiInfo = get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    factory {
        get<Retrofit>().create(SearchApiInfo::class.java)
    }

    single { provideSharedPref(androidApplication()) }


}

fun provideSharedPref(app: Application): SharedPreferences {
    return app.applicationContext.getSharedPreferences(
        SHARED_PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )
}