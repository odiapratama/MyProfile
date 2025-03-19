package com.problemsolver.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun <T> CoroutineScope.safeApiCall(
    onError: suspend (Exception) -> Unit = { },
    execute: suspend () -> T
) {
    launch {
        try {
            execute()
        } catch (e: Exception) {
            onError(e)
        }
    }
}