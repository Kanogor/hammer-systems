package ru.kanogor.hammersystems.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.kanogor.hammersystems.data.Repository
import ru.kanogor.hammersystems.data.SearchApiInfo

private const val BASE_URL = "https://pizzaallapala.p.rapidapi.com/"

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

}