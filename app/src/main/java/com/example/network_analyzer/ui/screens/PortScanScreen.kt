package com.example.network_analyzer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.network_analyzer.ui.componets.ErrorCard
import com.example.network_analyzer.ui.componets.LoadingScreenCard
import com.example.network_analyzer.ui.componets.PortScanResultCard
import com.example.network_analyzer.utils.UiState
import com.example.network_analyzer.viewmodel.NetworkViewModel

enum class ScanProtocol {
    TCP, UDP
}
@Composable
fun PortScanScreen(
    viewModel: NetworkViewModel
) {
    var host by remember { mutableStateOf("") }
    var startPort by remember { mutableStateOf("") }
    var endPort by remember { mutableStateOf("") }
    var selectedProtocol by remember { mutableStateOf(ScanProtocol.TCP) }

    val tcpState by viewModel.tcpScanState.collectAsState()
    val udpState by viewModel.udpScanState.collectAsState()

    val currentState =
        if (selectedProtocol == ScanProtocol.TCP) tcpState else udpState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Port Scan",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = host,
            onValueChange = { host = it },
            label = { Text("Host / IP Address") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = startPort,
            onValueChange = { startPort = it },
            label = { Text("Start Port") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = endPort,
            onValueChange = { endPort = it },
            label = { Text("End Port") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {

            Button(
                onClick = { selectedProtocol = ScanProtocol.TCP },
                modifier = Modifier.weight(1f)
            ) {
                Text("TCP")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { selectedProtocol = ScanProtocol.UDP },
                modifier = Modifier.weight(1f)
            ) {
                Text("UDP")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (host.isNotBlank() &&
                    startPort.isNotBlank() &&
                    endPort.isNotBlank()
                ) {
                    if (selectedProtocol == ScanProtocol.TCP) {
                        viewModel.tcpScan(
                            host,
                            startPort.toInt(),
                            endPort.toInt()
                        )
                    } else {
                        viewModel.udpScan(
                            host,
                            startPort.toInt(),
                            endPort.toInt()
                        )
                    }
                }
            }
        ) {
            Text("Start ${selectedProtocol.name} Scan")
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (currentState) {
            is UiState.IDLE -> {
                Text("Enter host and ports to start scanning")
            }

            is UiState.LOADING -> {
                LoadingScreenCard(text = "Scanning ${selectedProtocol.name} ports…")
            }

            is UiState.SUCCESS -> {
                PortScanResultCard(
                    currentState.data
                )
            }

            is UiState.ERROR -> {
                ErrorCard(
                    message = currentState.message
                )
            }
        }
    }
}
