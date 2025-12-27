package com.example.network_analyzer.data.model

data class NetworkInfoResponse(
    val localIP : String,
    val hostName: String,
    val os : String,
    val osVersion : String,
    val architecture : String,
    val macAddress:String
)
