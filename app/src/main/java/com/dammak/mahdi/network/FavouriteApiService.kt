package com.dammak.mahdi.network

import com.dammak.mahdi.data.Favourite
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private const val BASE_URL = "http://dinfo.ovh/restapi/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FavouriteApiService {
    @GET("sample")
    suspend fun getAllFavourites(): List<Favourite>
}

object FavouritesApi {
    val retrofitService: FavouriteApiService by lazy {
        retrofit.create(FavouriteApiService::class.java)
    }
}