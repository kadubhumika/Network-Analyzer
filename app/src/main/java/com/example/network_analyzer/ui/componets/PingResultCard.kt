package com.example.network_analyzer.ui.componets

import androidx.compose.foundation.layout.Column
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
import com.example.network_analyzer.data.model.PingResponse

@Composable
fun PingResultCard(response : PingResponse){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if(response.success){
                Color(0xFFE8F5E9) }
            else{Color(0xFFFFEBEE)}
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Ping Result", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(7.dp))

            Text("Success: ${response.success}")
            Text("Latency: ${response.latency}")
            Text("Status: ${if(response.success)"Success" else "Failed"}")
            Spacer(modifier = Modifier.padding(7.dp))
            Text(response.output)
        }
    }

}