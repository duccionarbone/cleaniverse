package com.duccionarbone.cleanarchitectured.domain.usecases

import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.Repo
import com.duccionarbone.cleanarchitectured.domain.repositories.GitHubRepository
import javax.inject.Inject

class GetReposUseCase @Inject constructor(private val gitHubRepository: GitHubRepository) {
    suspend operator fun invoke(userId: String) : Result<List<Repo>> = gitHubRepository.getRepos(userId)
}