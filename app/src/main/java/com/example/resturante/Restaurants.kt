package com.example.resturante

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("message" ) var restaurantes : ArrayList<Restaurants> = arrayListOf()
)

data class Restaurants(@SerializedName("id"                    ) var id                   : Int?               = null,
                       @SerializedName("nombre"                ) var nombre               : String?            = null,
                       @SerializedName("calificacion"          ) var calificacion         : String?               = null,
                       @SerializedName("anio"                  ) var anioCreacion         : String?            = null,
                       @SerializedName("costo"                 ) var costoPromedio        : String?               = null,
                       @SerializedName("fotos"                 ) var fotos                : ArrayList<String>? = null,
                       @SerializedName("direccion"             ) var direccion            : String?            = null,
                       @SerializedName("resenia"               ) var resenia              : String?            = null,
                       @SerializedName("x"                   ) var latitud             : String?            = null,
                       @SerializedName("y"                   ) var longitud             : String?            = null)

