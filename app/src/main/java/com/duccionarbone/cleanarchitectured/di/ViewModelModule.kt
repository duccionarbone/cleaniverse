package com.duccionarbone.cleanarchitectured.di

import androidx.lifecycle.ViewModel
import com.duccionarbone.cleanarchitectured.domain.usecases.GetMarsPhotosUseCase
import com.duccionarbone.cleanarchitectured.domain.usecases.GetNasaPhotosUseCase
import com.duccionarbone.cleanarchitectured.domain.usecases.GetReposUseCase
import com.duccionarbone.cleanarchitectured.domain.usecases.GetUserUseCase
import com.duccionarbone.cleanarchitectured.domain.usecases.GetUsersUseCase
import com.duccionarbone.presentation.home.HomeViewModel

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideMyViewModel(
        getUsersUseCase: GetUsersUseCase,
        getUserUseCase: GetUserUseCase,
        getReposUseCase: GetReposUseCase,
        getMarsPhotosUseCase: GetMarsPhotosUseCase,
        getNasaPhotosUseCase: GetNasaPhotosUseCase
    ): ViewModel {
        return HomeViewModel(getUsersUseCase, getUserUseCase, getReposUseCase, getMarsPhotosUseCase, getNasaPhotosUseCase)
    }
}