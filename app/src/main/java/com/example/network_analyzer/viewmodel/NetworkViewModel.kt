package com.example.network_analyzer.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network_analyzer.data.model.PingRequest
import com.example.network_analyzer.data.model.PortScanRequest
import com.example.network_analyzer.data.model.TracerouteRequest
import com.example.network_analyzer.repository.NetworkRepositoryImpl
import com.example.network_analyzer.utils.NetworkInfoUiState
import com.example.network_analyzer.utils.PingUiState
import com.example.network_analyzer.utils.TcpScanUiState
import com.example.network_analyzer.utils.TracerouteUiState
import com.example.network_analyzer.utils.UdpScanUiState
import com.example.network_analyzer.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NetworkViewModel () : ViewModel(){
    private val networkRepository = NetworkRepositoryImpl()

    private val _pingState = MutableStateFlow<PingUiState>(UiState.IDLE)
    val pingState : StateFlow<PingUiState>  = _pingState
    private val  _tcpScanState = MutableStateFlow<TcpScanUiState>(UiState.IDLE)
    val tcpScanState : StateFlow<TcpScanUiState> = _tcpScanState

    private val _udpScanState = MutableStateFlow<UdpScanUiState>(UiState.IDLE)
    val udpScanState : StateFlow<UdpScanUiState> = _udpScanState

    private val _tracerouteState = MutableStateFlow<TracerouteUiState>(UiState.IDLE)
    val tracerouteState : StateFlow<TracerouteUiState> = _tracerouteState

    private val _networkInfoState = MutableStateFlow<NetworkInfoUiState>(UiState.IDLE)
    val networkInfoState : StateFlow<NetworkInfoUiState> = _networkInfoState




    fun ping(host: String) {
        viewModelScope.launch {
            _pingState.value = UiState.LOADING
            try {
                val result = withContext(Dispatchers.IO) {
                    networkRepository.ping(PingRequest(host))
                }

                if (result.success) {
                    _pingState.value = UiState.SUCCESS(result)
                } else {
                    _pingState.value = UiState.ERROR("Ping failed")
                }
            } catch (e: Exception) {
                _pingState.value = UiState.ERROR("Network error")
            }
        }
    }


    fun tcpScan(host: String, startPort: Int, endPort: Int) {
        viewModelScope.launch {
            _tcpScanState.value = UiState.LOADING

            val result = networkRepository.tcpScan(
                PortScanRequest(host, startPort, endPort)
            )

            if (result.success) {
                _tcpScanState.value = UiState.SUCCESS(result)
            } else {
                _tcpScanState.value = UiState.ERROR(result.message)
            }
        }
    }


    fun udpScan(host: String, startPort: Int, endPort: Int) {
        viewModelScope.launch {
            _udpScanState.value = UiState.LOADING

            val result = networkRepository.udpScan(
                PortScanRequest(host, startPort, endPort)
            )

            if (result.success) {
                _udpScanState.value = UiState.SUCCESS(result)
            } else {
                _udpScanState.value = UiState.ERROR(result.message)
            }
        }
    }


    fun traceroute(target: String) {
        viewModelScope.launch {
            _tracerouteState.value = UiState.LOADING

            val result = networkRepository.traceroute(
                TracerouteRequest(target)
            )

            if (result.success) {
                _tracerouteState.value = UiState.SUCCESS(result)
            } else {
                _tracerouteState.value = UiState.ERROR(
                    result.error ?: "Traceroute failed"
                )
            }
        }
    }

    fun networkInfo() {
        viewModelScope.launch {
            _networkInfoState.value = UiState.LOADING

            val result = networkRepository.networkInfo()

            if (result.localIP != "Unknown") {
                _networkInfoState.value = UiState.SUCCESS(result)
            } else {
                _networkInfoState.value = UiState.ERROR("Unable to fetch network info")
            }
        }
    }


}













