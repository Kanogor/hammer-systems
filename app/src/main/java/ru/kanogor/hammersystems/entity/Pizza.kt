package ru.kanogor.hammersystems.entity

interface Productos {
    val productos: List<Pizza>
}

interface Pizza {
    val id: Int
    val name: String
    val descripcion: String?
    val image: String?
    val price: String?
}
