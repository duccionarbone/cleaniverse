package com.duccionarbone.cleanarchitectured.domain.repositories

import com.duccionarbone.cleanarchitectured.domain.entities.Nasa.MarsPhotos
import com.duccionarbone.cleanarchitectured.domain.entities.Nasa.NasaPhotos

interface NasaRepository {
    suspend fun getMarsPhotos(rover: String, sol: Int, apiKey: String): Result<MarsPhotos>
    suspend fun getNasaPhotos(query: String, mediaType: String): Result<NasaPhotos>
}