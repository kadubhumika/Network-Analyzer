package com.example.network_analyzer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.network_analyzer.ui.componets.TracerouteResultCard
import com.example.network_analyzer.utils.UiState
import com.example.network_analyzer.viewmodel.NetworkViewModel

@Composable
fun TracerouteScreen(viewModel: NetworkViewModel){
    var target by remember { mutableStateOf("") }
    val state by viewModel.tracerouteState.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Traceroute", fontWeight = FontWeight.ExtraBold)

        Spacer(modifier = Modifier.padding(25.dp))
        OutlinedTextField(
            value = target,
            onValueChange = { target = it },
            label = { Text("Enter IP/Host") },
            )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = {if(target.isNotEmpty()) viewModel.traceroute(target)}) {
            Text(text = "Traceroute")
        }
        Spacer(modifier = Modifier.padding(16.dp))
        when(state){
            is UiState.IDLE->{
                Text(text = "Enter Host and Press Traceroute")
            }
            is UiState.LOADING->{
                Text(text = "Loading...")
            }
            is UiState.SUCCESS->{
                val data = (state as UiState.SUCCESS).data
                TracerouteResultCard(data)
            }
            is UiState.ERROR->{
                Text(text = (state as UiState.ERROR).message)
            }

        }
    }


}