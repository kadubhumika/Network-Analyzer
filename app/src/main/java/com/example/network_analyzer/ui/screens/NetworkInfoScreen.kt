package com.example.network_analyzer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.network_analyzer.ui.componets.ErrorCard
import com.example.network_analyzer.ui.componets.InfoCard
import com.example.network_analyzer.ui.componets.LoadingScreenCard
import com.example.network_analyzer.ui.componets.PingResultCard
import com.example.network_analyzer.utils.UiState
import com.example.network_analyzer.viewmodel.NetworkViewModel

@Composable
fun NetworkInfoScreen(
    viewModel: NetworkViewModel
) {
    val state by viewModel.networkInfoState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.networkInfo()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Network Information",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is UiState.LOADING -> {
                LoadingScreenCard(text = "Fetching network info…")
            }

            is UiState.SUCCESS -> {
                val data = (state as UiState.SUCCESS).data
                InfoCard(title = "Network Information", info = mapOf(
                    "Local IP" to data.localIP,
                    "Host Name" to data.hostName,
                    "OS" to data.os,
                    "OS Version" to data.osVersion,
                    "Architecture" to data.architecture,
                    "MAC Address" to data.macAddress
                ))

            }

            is UiState.ERROR -> {
                ErrorCard(message = (state as UiState.ERROR).message)
            }

            UiState.IDLE -> {
                Text("Idle")
            }
        }
    }
}
