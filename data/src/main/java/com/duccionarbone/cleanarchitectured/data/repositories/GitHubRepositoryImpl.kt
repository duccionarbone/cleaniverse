package com.duccionarbone.cleanarchitectured.data.repositories

import com.duccionarbone.cleanarchitectured.data.remote.GitHub.GithubApi
import com.duccionarbone.cleanarchitectured.data.repositories.base.BaseRepository
import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.Repo
import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.User
import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.UserDetail
import com.duccionarbone.cleanarchitectured.domain.repositories.GitHubRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class GitHubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val dispatcher: CoroutineDispatcher,
) : GitHubRepository, BaseRepository() {

    override suspend fun getUsers(): Result<List<User>> = makeApiCall(dispatcher) {
        githubApi.getUsers()
    }

    override suspend fun getUser(userId: String): Result<UserDetail?> = makeApiCall(dispatcher) {
        githubApi.getUser(userId)
    }

    override suspend fun getRepos(userId: String): Result<List<Repo>> = makeApiCall(dispatcher) {
        githubApi.getRepos(userId)
    }

}

