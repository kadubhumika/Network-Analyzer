package com.example.network_analyzer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.network_analyzer.ui.screens.NetworkInfoScreen
import com.example.network_analyzer.ui.screens.PingScreen
import com.example.network_analyzer.ui.screens.PortScanScreen
import com.example.network_analyzer.ui.screens.TracerouteScreen
import com.example.network_analyzer.utils.ScreenState
import com.example.network_analyzer.viewmodel.NetworkViewModel

@Composable
fun AppNavBar(viewModel: NetworkViewModel){
    val navController = rememberNavController()

    Scaffold(bottomBar = {BottomNavBar(navController)}) {
        padding ->
        NavHost(
            navController = navController,
            startDestination = ScreenState.Ping.route,
            modifier = androidx.compose.ui.Modifier.padding(padding)
        ){
            composable(ScreenState.Ping.route){
                PingScreen(viewModel)
            }
            composable(ScreenState.Traceroute.route){
                TracerouteScreen(viewModel)
            }
            composable(ScreenState.PortScan.route){
                PortScanScreen(viewModel)
            }
            composable(ScreenState.NetworkInfo.route){
                NetworkInfoScreen(viewModel)
            }


        }
    }

}
@Composable
fun BottomNavBar(navController: NavController){
    val items = listOf(
        ScreenState.Ping,
        ScreenState.Traceroute,
        ScreenState.PortScan,
        ScreenState.NetworkInfo
    )
    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach{
            screen->
            NavigationBarItem(
                icon =  {
                    Icon(
                        imageVector = screen.icons,
                        contentDescription = screen.title
                    ) },
                label = {
                    Text(text = screen.title)
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true


                    }

                }

            )
        }

    }

}