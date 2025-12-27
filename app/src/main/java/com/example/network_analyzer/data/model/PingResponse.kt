package com.example.network_analyzer.data.model



data class PingResponse(
    val host: String,
    val output: String,
    val latency: String?,
    val success: Boolean
)