package com.duccionarbone.cleanarchitectured.domain.usecases

import com.duccionarbone.cleanarchitectured.domain.repositories.NasaRepository
import javax.inject.Inject

class GetNasaPhotosUseCase @Inject constructor(private val nasaRepository: NasaRepository) {
    suspend operator fun invoke(query: String, mediaType: String) = nasaRepository.getNasaPhotos(query, mediaType)
}