package com.duccionarbone.cleanarchitectured.domain.repositories

import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.Repo
import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.User
import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.UserDetail


interface GitHubRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUser(userId: String): Result<UserDetail?>
    suspend fun getRepos(userId: String): Result<List<Repo>>
}