package com.dammak.mahdi.network

import com.dammak.mahdi.domain.Favourite
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

/**
 * Implementation of the remote data source (network).
 */
interface FavouritesRemoteDataSourceImp : FavouritesRemoteDataSource {
    @GET("sample")
    override suspend fun getAllFavourites(): List<Favourite>
}

object Api {
    val retrofitService: FavouritesRemoteDataSourceImp by lazy {
        retrofit.create(FavouritesRemoteDataSourceImp::class.java)
    }
}