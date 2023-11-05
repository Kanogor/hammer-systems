package ru.kanogor.hammersystems.data


class Repository(val searchApiInfo: SearchApiInfo) {

    suspend fun getPizzas(): ProductosDto {
        return searchApiInfo.getPizzas().body()!!
    }

}