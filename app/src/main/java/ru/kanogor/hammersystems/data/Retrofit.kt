package ru.kanogor.hammersystems.data


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface SearchApiInfo {

    @Headers(
        "X-RapidAPI-Key: ${ApiKeys.api_key}",
        "X-RapidAPI-Host: ${ApiKeys.host}"
    )
    @GET("productos")
    suspend fun getPizzas(): Response<ProductosDto>

}