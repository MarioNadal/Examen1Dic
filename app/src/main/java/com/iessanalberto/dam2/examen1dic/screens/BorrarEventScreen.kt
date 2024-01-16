package com.iessanalberto.dam2.examen1dic.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iessanalberto.dam2.examen1dic.data.allEvents
import com.iessanalberto.dam2.examen1dic.navigation.AppScreens
import com.iessanalberto.dam2.examen1dic.viewmodels.BorrarEventScreenViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorrarEventScreen(navController: NavHostController, borrarEventScreenViewModel: BorrarEventScreenViewModel){
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Borrar evento") },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            navigationIcon = {
                IconButton(onClick = {navController.navigate(AppScreens.MainScreen.route)}) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val autor : String by borrarEventScreenViewModel.autor.observeAsState(initial = "")
            val fecha : String by borrarEventScreenViewModel.fecha.observeAsState(initial = "")
            val time : String by borrarEventScreenViewModel.time.observeAsState(initial = "")
            val isEventoAdded : Boolean by borrarEventScreenViewModel.isEventAdded.observeAsState(initial = false)
            val mCalendar = Calendar.getInstance()
            val anio: Int
            val mes: Int
            val dia: Int
            val hora: Int = 12
            val minuto: Int = 0
            anio = mCalendar.get(Calendar.YEAR)
            mes = mCalendar.get(Calendar.MONTH)
            dia = mCalendar.get(Calendar.DAY_OF_MONTH)
            val mDatePickerDialog =
                DatePickerDialog(LocalContext.current, { DatePicker, anio: Int, mes: Int, dia: Int ->
                    borrarEventScreenViewModel.onChanged(fechaUi = "$dia/${mes + 1}/$anio", timeUi = time, autorUi = autor)
                }, anio, mes, dia)
            val mHourPickerDialog =
                TimePickerDialog(LocalContext.current, { TimePicker, hora: Int, minuto: Int ->
                    borrarEventScreenViewModel.onChanged(fechaUi = fecha, timeUi = "$hora:$minuto",autorUi = autor)

                }, hora, minuto, true)
            var context = LocalContext.current
            Column(modifier = Modifier.padding(15.dp)) {
                Text(
                    text = "App de gestión de eventos y citas",
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = autor, onValueChange = {borrarEventScreenViewModel.onChanged(fechaUi = fecha, timeUi=time,  autorUi = it)},
                    label = { Text(text = "Introduce tu nombre") }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(value = fecha, onValueChange = {borrarEventScreenViewModel.onChanged(fechaUi = it, timeUi=time,  autorUi = autor)},
                        readOnly = true, label = {
                            Text(text = "Elige fecha")
                        })
                    Icon(imageVector = Icons.Filled.DateRange,
                        contentDescription = "Elige fecha",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .clickable {
                                mDatePickerDialog.show()
                            })
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(value = time, onValueChange = {borrarEventScreenViewModel.onChanged(fechaUi = fecha, timeUi=it,  autorUi = autor)},
                        readOnly = true, label = { Text(text = "Elige hora") })
                    Icon(imageVector = Icons.Filled.DateRange,
                        contentDescription = "Elige hora",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .clickable {
                                mHourPickerDialog.show()
                            })
                }
                Button(onClick = {
                    //recorremos la lista con todos los eventos
                    for(evento in allEvents){
                        //Si los datos coinciden borrarrá el evento que coincide, activará el toast y irá a la pantalla principal
                        if(evento.date==fecha&&evento.hour==time&&evento.author==autor){
                            allEvents.remove(evento)
                            borrarEventScreenViewModel.onListChanged(true)
                            navController.navigate(AppScreens.MainScreen.route)
                        }
                    }

                }) {
                    Text(text = "Borrar evento")
                }

                //Cuando el eveneto sea añaido se enseñará el Toast y se pondrá en false para que no se enseñe infinitamente
                if(isEventoAdded){
                    Toast.makeText(context, "Evento borrado correctamente por: " + autor, Toast.LENGTH_SHORT).show()
                    borrarEventScreenViewModel.onListChanged(false)
                }
            }
        }
    }

}