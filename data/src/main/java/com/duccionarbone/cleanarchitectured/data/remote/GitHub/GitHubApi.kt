package com.duccionarbone.cleanarchitectured.data.remote.GitHub

import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.Repo
import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.User
import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.UserDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET(GithubEndpoints.GET_USERS)
    suspend fun getUsers(): List<User>

    @GET(GithubEndpoints.GET_USER)
    suspend fun getUser(@Path("userLogin") userId: String): UserDetail?

    @GET(GithubEndpoints.GET_REPOS_BY_USER)
    suspend fun getRepos(@Path("userLogin") userId: String): List<Repo>
}