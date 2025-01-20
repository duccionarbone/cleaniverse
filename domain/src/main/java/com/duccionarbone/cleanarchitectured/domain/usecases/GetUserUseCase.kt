package com.duccionarbone.cleanarchitectured.domain.usecases

import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.UserDetail
import com.duccionarbone.cleanarchitectured.domain.repositories.GitHubRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val gitHubRepository: GitHubRepository) {
    suspend operator fun invoke(userId: String) : Result<UserDetail?> = gitHubRepository.getUser(userId)
}