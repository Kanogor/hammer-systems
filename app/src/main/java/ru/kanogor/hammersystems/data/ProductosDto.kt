package ru.kanogor.hammersystems.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.kanogor.hammersystems.entity.Pizza
import ru.kanogor.hammersystems.entity.Productos

@JsonClass(generateAdapter = true)
data class ProductosDto(
    @Json(name = "productos")
    override val productos: List<PizzaDto>
) : Productos

@JsonClass(generateAdapter = true)
data class PizzaDto(
    @Json(name = "id")
    override val id: Int,
    @Json(name = "nombre")
    override val name: String,
    @Json(name = "descripcion")
    override val descripcion: String?,
    @Json(name = "linkImagen")
    override val image: String?,
    @Json(name = "precio")
    override val price: String?
) : Pizza