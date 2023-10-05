package com.example.littlelemoncapstone

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun SnackBar(
    message: String,
    duration: SnackbarDuration = SnackbarDuration.Short,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarHostState) {
        snackbarHostState.showSnackbar(message, duration = duration)
    }
    SnackbarHost(hostState = snackbarHostState)
}
