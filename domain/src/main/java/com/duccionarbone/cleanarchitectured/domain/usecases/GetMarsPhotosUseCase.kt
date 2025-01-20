package com.duccionarbone.cleanarchitectured.domain.usecases

import com.duccionarbone.cleanarchitectured.domain.entities.Nasa.MarsPhotos
import com.duccionarbone.cleanarchitectured.domain.repositories.NasaRepository
import javax.inject.Inject

class GetMarsPhotosUseCase @Inject constructor(private val nasaRepository: NasaRepository) {
    suspend operator fun invoke(rover: String, sol: Int, apiKey: String) : Result<MarsPhotos> = nasaRepository.getMarsPhotos(rover, sol, apiKey)
}