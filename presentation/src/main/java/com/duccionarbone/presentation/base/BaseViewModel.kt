package com.duccionarbone.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {

    private var _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: MutableStateFlow<UiState> = _uiState

    protected suspend fun <T> safeApiCall(
        apiCall: suspend () -> Result<T>,
        onSuccess: (T?) -> Unit = { },
        onError: (Exception?) -> Unit = { }
    ) {
        _uiState.value = UiState.Loading
        try {
            val result = apiCall()
            onSuccess(result.getOrNull())
            _uiState.value = UiState.Success
        } catch (e: Exception) {
            onError(e)
            _uiState.value = UiState.Error
        }
    }
}

enum class UiState {
    Idle,
    Loading,
    Success,
    Error
}

