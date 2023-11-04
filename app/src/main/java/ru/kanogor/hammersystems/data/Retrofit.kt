package ru.kanogor.hammersystems.data


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface SearchApiInfo {

    @Headers(
        "X-RapidAPI-Key: $api_key",
        "X-RapidAPI-Host: $host"
    )
    @GET("productos")
    suspend fun getPizzas(): Response<ProductosDto>

    private companion object {
        private const val api_key = "21c87bcbc5msh4ed8a65bf094332p152917jsn65ec2ea0777b"
        private const val host = "pizzaallapala.p.rapidapi.com"
    }
}