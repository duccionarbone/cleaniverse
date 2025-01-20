package com.duccionarbone.cleanarchitectured.data.remote.GitHub

object GithubEndpoints {
    const val BASE_URL = "https://api.github.com/"

    const val GET_USERS = "users?since=51234842"
    const val GET_USER = "users/{userLogin}"
    const val GET_REPOS_BY_USER = "users/{userLogin}/repos?sort=stars&order=desc"
}