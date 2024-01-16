package com.iessanalberto.dam2.examen1dic.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iessanalberto.dam2.examen1dic.data.allEvents
import com.iessanalberto.dam2.examen1dic.models.EventCard
import com.iessanalberto.dam2.examen1dic.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Eventos") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                actions = {
                    Text(text = "Gestión eventos")
                    IconButton(onClick = { navController.navigate(AppScreens.BorrarEventScreen.route) }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Delete")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(AppScreens.NewEventScreen.route) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "New event")

            }
        }
    ) { paddingValues ->
        Column(){
            MainScreenBodyContent(modifier = Modifier.padding(paddingValues))
        }

    }
}
@Composable
fun MainScreenBodyContent(modifier: Modifier) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ){
        items(allEvents){
            event ->
            EventCard(evento = event)
        }
    }

}


