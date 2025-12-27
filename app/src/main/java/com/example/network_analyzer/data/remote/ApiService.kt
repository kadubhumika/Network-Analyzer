package com.example.network_analyzer.data.remote

import com.example.network_analyzer.data.model.NetworkInfoResponse
import com.example.network_analyzer.data.model.PingRequest
import com.example.network_analyzer.data.model.PingResponse
import com.example.network_analyzer.data.model.PortScanRequest
import com.example.network_analyzer.data.model.PortScanResponse
import com.example.network_analyzer.data.model.TracerouteRequest
import com.example.network_analyzer.data.model.TracerouteResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/ping")
    suspend fun ping(@Body request: PingRequest): PingResponse

    @POST("/traceroute")
    suspend fun traceroute(@Body request: TracerouteRequest): TracerouteResponse

    @POST("/tcp-scan")
    suspend fun tcpScan(@Body request: PortScanRequest): PortScanResponse

    @POST("/udp-scan")
    suspend fun udpScan(@Body request: PortScanRequest): PortScanResponse

    @GET("/network-info")
    suspend fun networkInfo(): NetworkInfoResponse


}







