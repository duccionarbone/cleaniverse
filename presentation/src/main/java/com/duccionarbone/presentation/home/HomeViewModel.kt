package com.duccionarbone.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.User
import com.duccionarbone.cleanarchitectured.domain.entities.Nasa.MarsPhoto
import com.duccionarbone.cleanarchitectured.domain.entities.Nasa.NasaPhoto
import com.duccionarbone.cleanarchitectured.domain.usecases.GetMarsPhotosUseCase
import com.duccionarbone.cleanarchitectured.domain.usecases.GetNasaPhotosUseCase
import com.duccionarbone.cleanarchitectured.domain.usecases.GetReposUseCase
import com.duccionarbone.cleanarchitectured.domain.usecases.GetUserUseCase
import com.duccionarbone.cleanarchitectured.domain.usecases.GetUsersUseCase
import com.duccionarbone.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getReposUseCase: GetReposUseCase,
    private val getMarsPhotosUseCase: GetMarsPhotosUseCase,
    private val getNasaPhotosUseCase: GetNasaPhotosUseCase
) : BaseViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _marsPhotos = MutableStateFlow<List<MarsPhoto>>(emptyList())
    val marsPhotos: StateFlow<List<MarsPhoto>> = _marsPhotos

    private val _nasaPhotos = MutableStateFlow<List<NasaPhoto>>(emptyList())
    val nasaPhotos: StateFlow<List<NasaPhoto>> = _nasaPhotos

    var actualQuery = ""

    fun getUsers() {
        viewModelScope.launch {
            safeApiCall(
                apiCall = { getUsersUseCase() },
                onSuccess = { users ->
                    users?.forEach { println("user: $it") }
                    _users.update { users ?: emptyList() }
                },
                onError = { e ->
                    Log.e("HomeViewModel", "Error fetching users ${e?.message}}", e)
                }
            )
        }
    }

    fun getUser(userId: String) {
        viewModelScope.launch {
            safeApiCall(
                apiCall = { getUserUseCase(userId) },
                onSuccess = { user ->
                    println("user: ${user?.name}")
                },
                onError = { e ->
                    Log.e("HomeViewModel", "Error fetching user ${e?.message}}", e)
                }
            )
        }
    }

    fun getRepos(userId: String) {
        viewModelScope.launch {
            safeApiCall(
                apiCall = { getReposUseCase(userId) },
                onSuccess = { repos ->
                    repos?.forEach { println("repo: $it") }
                },
                onError = { e ->
                    Log.e("HomeViewModel", "Error fetching repos ${e?.message}}", e)
                }
            )
        }
    }

    fun getMarsPhotos(rover: String, sol: Int, apiKey: String) {
        viewModelScope.launch {
            safeApiCall(
                apiCall = { getMarsPhotosUseCase(rover, sol, apiKey) },
                onSuccess = { marsPhotos ->
                    _marsPhotos.update { marsPhotos?.photos ?: emptyList() }
                },
                onError = { e ->
                    Log.e("HomeViewModel", "Error fetching mars photos ${e?.message}}", e)
                }
            )
        }
    }

    fun getNasaPhotos(query: String, mediaType: String) {
        viewModelScope.launch {
            safeApiCall(
                apiCall = { getNasaPhotosUseCase(query, mediaType) },
                onSuccess = { nasaPhotos ->
                    _nasaPhotos.value =  nasaPhotos?.collection?.items ?: emptyList()
                },
                onError = { e ->
                    Log.e("HomeViewModel", "Error fetching nasa photos ${e?.message}}", e)
                }
            )
        }
    }

    fun updateFiltersAndCallApi(query: String) {
        actualQuery = query
        getNasaPhotos(query, "image")
    }

}