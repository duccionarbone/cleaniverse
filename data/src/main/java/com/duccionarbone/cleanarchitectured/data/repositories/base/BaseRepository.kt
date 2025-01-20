package com.duccionarbone.cleanarchitectured.data.repositories.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

open class BaseRepository {

    suspend fun <T> makeApiCall(
        dispatcher: CoroutineDispatcher,
        call: suspend () -> T
    ): Result<T> = runCatching {
        withContext(dispatcher) {
            call.invoke()
        }
    }

}