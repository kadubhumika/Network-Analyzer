package com.example.network_analyzer.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenState (val route : String,val title: String,val icons: ImageVector){
    object Ping : ScreenState("Ping","Ping", Icons.Default.Notifications)
    object PortScan : ScreenState("PortScan","PortScan", Icons.Default.Notifications)
    object Traceroute : ScreenState("Traceroute","Traceroute", Icons.Default.FavoriteBorder)
    object NetworkInfo : ScreenState("NetworkInfo","NetworkInfo", Icons.Default.FavoriteBorder)

}