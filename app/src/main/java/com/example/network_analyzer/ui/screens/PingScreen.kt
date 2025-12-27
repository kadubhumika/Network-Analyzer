package com.example.network_analyzer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import com.example.network_analyzer.ui.componets.PingResultCard
import com.example.network_analyzer.utils.UiState
import com.example.network_analyzer.viewmodel.NetworkViewModel

@Composable
fun PingScreen(viewModel: NetworkViewModel){
    val state by viewModel.pingState.collectAsState()
    var host by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Ping Test",  fontWeight = FontWeight.ExtraBold)

        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(
            value = host,
            onValueChange = { host = it },
            label = { Text("Enter IP/Host") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = {if(host.isNotEmpty()) viewModel.ping(host)}, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Ping")
        }
        Spacer(modifier = Modifier.padding(25.dp))
        when(state){
            is UiState.IDLE -> {
                Text(text = "Enter Host and Press Ping")
            }
            is UiState.LOADING -> {
                Text(text = "Loading...")
            }
            is UiState.SUCCESS -> {
                PingResultCard((state as UiState.SUCCESS).data)
            }
            is UiState.ERROR -> {
                Text(text = (state as UiState.ERROR).message)
            }
        }




        }



    }



