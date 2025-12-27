package com.example.network_analyzer.utils

import com.example.network_analyzer.data.model.NetworkInfoResponse
import com.example.network_analyzer.data.model.PingResponse
import com.example.network_analyzer.data.model.PortScanResponse
import com.example.network_analyzer.data.model.TracerouteResponse

// Here we will create data class for each

// instead of many booleans and all we will make sealed class for that all state
sealed class UiState<out T : Any>{
    object IDLE : UiState<Nothing>()
    object LOADING : UiState<Nothing>()
    data class SUCCESS<out T : Any>(val data : T) : UiState<T>()
    data class ERROR(val message : String) : UiState<Nothing>()
}

typealias PingUiState = UiState<PingResponse>


typealias TcpScanUiState = UiState<PortScanResponse>


typealias UdpScanUiState = UiState<PortScanResponse>



typealias TracerouteUiState = UiState<TracerouteResponse>

typealias NetworkInfoUiState = UiState<NetworkInfoResponse>