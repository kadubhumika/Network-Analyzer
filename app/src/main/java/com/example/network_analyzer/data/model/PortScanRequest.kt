package com.example.network_analyzer.data.model

data class PortScanRequest(
    val host: String,
    val startPort : Int,
    val endPort :Int
)