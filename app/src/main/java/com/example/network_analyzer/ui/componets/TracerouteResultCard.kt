package com.example.network_analyzer.ui.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.packInts
import com.example.network_analyzer.data.model.Hop
import com.example.network_analyzer.data.model.TracerouteResponse

@Composable
fun TracerouteResultCard(response : TracerouteResponse){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Traceroute Result", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(7.dp))

            Text("Target: ${response.target}")
            Text("TTL: ${response.ttl}")
            Text("Packet Size: ${response.bytePackets}")

            Spacer(modifier = Modifier.padding(12.dp))

            response.hops.forEach {
                HopItem(hop = it)
            }


        }
    }

}
@Composable
fun HopItem(hop: Hop){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Text("Hop ${hop.hop}")
            Text(hop.ip)
            Text(
                text = hop.latency?.let { "$it ms" } ?: "*",
                color = if (hop.success) Color.Green else Color.Red
            )
        }
    }

}

