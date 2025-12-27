package com.example.network_analyzer.repository

import androidx.compose.ui.util.packInts
import com.example.network_analyzer.data.model.NetworkInfoResponse
import com.example.network_analyzer.data.model.PingRequest
import com.example.network_analyzer.data.model.PingResponse
import com.example.network_analyzer.data.model.PortScanRequest
import com.example.network_analyzer.data.model.PortScanResponse
import com.example.network_analyzer.data.model.TracerouteRequest
import com.example.network_analyzer.data.model.TracerouteResponse
import com.example.network_analyzer.data.remote.ApiClient
import com.example.network_analyzer.data.remote.ApiService

class NetworkRepositoryImpl {

    private val api: ApiService = ApiClient.api

    suspend fun ping(request: PingRequest): PingResponse {
        return try {
            api.ping(request)
        } catch (e: Exception) {
            e.printStackTrace()

            PingResponse(
                host = request.host,
                output = "Ping failed",
                latency = null,
                success = false
            )
        }
    }

    suspend fun traceroute(request: TracerouteRequest): TracerouteResponse {
        return try {
            api.traceroute(request)
        } catch (e: Exception) {
            TracerouteResponse(
                target = request.target,
                output = "Traceroute failed",
                hops = emptyList(),
                ttl = -1,
                success = false,
                error = null,
                bytePackets = -1
            )
        }
    }

    suspend fun tcpScan(request: PortScanRequest): PortScanResponse {
        return try {
            api.tcpScan(request)
        } catch (e: Exception) {
            PortScanResponse(
                host = request.host,
                protocol = "tcp",
                ports = emptyList(),
                scan_time_ms = -1,
                success = false,
                message = "TCP scan failed"
            )
        }
    }

    suspend fun udpScan(request: PortScanRequest): PortScanResponse {
        return try {
            api.udpScan(request)
        } catch (e: Exception) {
            PortScanResponse(
                host = request.host,
                protocol = "udp",
                ports = emptyList(),
                scan_time_ms = -1,
                success = false,
                message = "UDP scan failed"
            )
        }
    }

    suspend fun networkInfo(): NetworkInfoResponse {
        return try {
            api.networkInfo()
        } catch (e: Exception) {
            NetworkInfoResponse(
                localIP = "Unknown",
                hostName = "Unknown",
                os = "Unknown",
                osVersion = "Unknown",
                architecture = "Unknown",
                macAddress = "Unknown"
            )
        }
    }
}
