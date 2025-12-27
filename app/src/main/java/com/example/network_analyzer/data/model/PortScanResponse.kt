package com.example.network_analyzer.data.model

data class PortScanResponse(
    val host: String,
    val protocol: String,
    val ports: List<OpenPorts>,
    val scan_time_ms: Int,
    val success: Boolean,
    val message: String
)
data class OpenPorts(
    val portNumber: Int,
    val serviceName: String,
    val state: State
)
enum class State {
    OPEN
}


