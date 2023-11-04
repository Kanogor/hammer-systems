package ru.kanogor.hammersystems.data

import ru.kanogor.hammersystems.entity.Pizza

class Repository(val searchApiInfo: SearchApiInfo) {

    suspend fun getPizzas(): List<Pizza> {
        return searchApiInfo.getPizzas().body()!!.productos
    }

}