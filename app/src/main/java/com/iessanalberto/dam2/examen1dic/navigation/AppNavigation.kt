package com.iessanalberto.dam2.examen1dic.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iessanalberto.dam2.examen1dic.screens.BorrarEventScreen
import com.iessanalberto.dam2.examen1dic.screens.MainScreen
import com.iessanalberto.dam2.examen1dic.screens.NewEventScreen
import com.iessanalberto.dam2.examen1dic.viewmodels.BorrarEventScreenViewModel
import com.iessanalberto.dam2.examen1dic.viewmodels.NewEventScreenViewModel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route ){
        composable(route = AppScreens.MainScreen.route) { MainScreen(navController)}
        composable(route = AppScreens.NewEventScreen.route) { NewEventScreen(navController, newEventScreenViewModel = NewEventScreenViewModel())}
        composable(route = AppScreens.BorrarEventScreen.route) { BorrarEventScreen(navController, borrarEventScreenViewModel = BorrarEventScreenViewModel()) }
    }
}