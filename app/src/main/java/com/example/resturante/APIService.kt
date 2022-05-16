package com.example.resturante

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getAllRestaurants(@Url url: String) : Response<RestaurantResponse>
}