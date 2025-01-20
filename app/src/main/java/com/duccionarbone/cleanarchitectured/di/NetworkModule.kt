package com.duccionarbone.cleanarchitectured.di

import com.duccionarbone.cleanarchitectured.data.remote.GitHub.GithubApi
import com.duccionarbone.cleanarchitectured.data.remote.GitHub.GithubEndpoints
import com.duccionarbone.cleanarchitectured.data.remote.Nasa.NasaApi
import com.duccionarbone.cleanarchitectured.data.remote.Nasa.NasaEndpoints
import com.duccionarbone.cleanarchitectured.data.repositories.GitHubRepositoryImpl
import com.duccionarbone.cleanarchitectured.data.repositories.NasaRepositoryImpl
import com.duccionarbone.cleanarchitectured.domain.repositories.GitHubRepository
import com.duccionarbone.cleanarchitectured.domain.repositories.NasaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        // Create a logging interceptor
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Create an OkHttpClient with the logging interceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(NasaEndpoints.NASA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun bindGitHubRepository(gitHubRepositoryImpl: GitHubRepositoryImpl): GitHubRepository {
        return gitHubRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideNasaApi(retrofit: Retrofit): NasaApi {
        return retrofit.create(NasaApi::class.java)
    }

    @Provides
    @Singleton
    fun bindNasaRepository(nasaRepositoryImpl: NasaRepositoryImpl): NasaRepository {
        return nasaRepositoryImpl
    }
}