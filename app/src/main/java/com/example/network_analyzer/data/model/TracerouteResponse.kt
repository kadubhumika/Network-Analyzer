package com.example.network_analyzer.data.model

data class TracerouteResponse(
    val target: String,
    val output: String,
    val hops: List<Hop>,
    val ttl : Int,
    val success: Boolean,
    val error: String?,
    val bytePackets : Int
)
data class Hop(
    val hop : Int,
    val ip: String,

    val latency: Long,
    val ttl : Int?,
    val success: Boolean
)

