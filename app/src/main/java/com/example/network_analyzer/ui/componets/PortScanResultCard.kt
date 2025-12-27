package com.example.network_analyzer.ui.componets

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.network_analyzer.data.model.OpenPorts
import com.example.network_analyzer.data.model.PortScanResponse
import com.example.network_analyzer.data.model.State

@Composable
fun PortScanResultCard(response: PortScanResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Port Scan (${response.protocol})", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Text("Host: ${response.host}")
            Spacer(modifier = Modifier.height(8.dp))

            response.ports.forEach { port ->
                PortItem(port)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(response.message)
        }
    }
}
@Composable
fun PortItem(port : OpenPorts){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween

    ) {
        Text("Port: ${port.portNumber}")
        Text(port.serviceName)
        Text(
            port.state.name,
            color = if(port.state == State.OPEN) Color.Green else{Color.Gray}
        )
    }

}
