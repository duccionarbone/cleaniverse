package com.duccionarbone.cleanarchitectured.domain.usecases

import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.User
import com.duccionarbone.cleanarchitectured.domain.repositories.GitHubRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val gitHubRepository: GitHubRepository) {
    suspend operator fun invoke() : Result<List<User>> = gitHubRepository.getUsers()
}