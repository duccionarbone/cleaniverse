package com.duccionarbone.cleanarchitectured.data.repositories

import com.duccionarbone.cleanarchitectured.data.remote.Nasa.NasaApi
import com.duccionarbone.cleanarchitectured.data.repositories.base.BaseRepository
import com.duccionarbone.cleanarchitectured.domain.repositories.NasaRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class NasaRepositoryImpl @Inject constructor(
    private val nasaApi: NasaApi,
    private val dispatcher: CoroutineDispatcher,
): NasaRepository, BaseRepository() {

    override suspend fun getMarsPhotos(rover: String, sol: Int, apiKey: String) = makeApiCall(dispatcher) {
        nasaApi.getMarsPhotos(rover, sol, apiKey)
    }

    override suspend fun getNasaPhotos(query: String, mediaType: String) = makeApiCall(dispatcher) {
        nasaApi.getNasaPhotos(query, mediaType)
    }

}