package com.duccionarbone.cleanarchitectured.data.remote.Nasa

import com.duccionarbone.cleanarchitectured.domain.entities.Nasa.MarsPhotos
import com.duccionarbone.cleanarchitectured.domain.entities.Nasa.NasaPhotos
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NasaApi {

    @GET(NasaEndpoints.GET_MARS_PHOTOS)
    suspend fun getMarsPhotos(
        @Path("rover") rover: String,
        @Query("sol") sol: Int,
        @Query("api_key") apiKey: String
    ): MarsPhotos

    @GET(NasaEndpoints.GET_NASA_PHOTOS)
    suspend fun getNasaPhotos(
        @Query("q") query: String,
        @Query("media_type") mediaType: String
    ): NasaPhotos

}