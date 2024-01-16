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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.iessanalberto.dam2.examen1dic.data.allEvents
import com.iessanalberto.dam2.examen1dic.data.listaTodasPersonas
import com.iessanalberto.dam2.examen1dic.models.Evento
import com.iessanalberto.dam2.examen1dic.navigation.AppScreens
import com.iessanalberto.dam2.examen1dic.viewmodels.NewEventScreenViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewEventScreen(navController: NavHostController, newEventScreenViewModel: NewEventScreenViewModel) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Crear evento") },
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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NewEventScreenBodyContent(modifier = Modifier.padding(paddingValues), newEventScreenViewModel, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewEventScreenBodyContent(modifier: Modifier, newEventScreenViewModel: NewEventScreenViewModel, navController: NavController) {
    Column(modifier = modifier.padding(15.dp)) {
        val fecha: String by newEventScreenViewModel.fecha.observeAsState(initial = "")
        val time : String by newEventScreenViewModel.time.observeAsState(initial = "")
        val titulo : String by newEventScreenViewModel.titulo.observeAsState(initial = "")
        val autor : String by newEventScreenViewModel.autor.observeAsState(initial = "")
        val recurso : String by newEventScreenViewModel.recurso.observeAsState(initial = "")
        val description : String by newEventScreenViewModel.description.observeAsState(initial = "")
        val isEventoAdded : Boolean by newEventScreenViewModel.isEventAdded.observeAsState(initial = false)
        val anio: Int
        val mes: Int
        val dia: Int
        val hora: Int = 12
        val minuto: Int = 0
        val mCalendar = Calendar.getInstance()
        anio = mCalendar.get(Calendar.YEAR)
        mes = mCalendar.get(Calendar.MONTH)
        dia = mCalendar.get(Calendar.DAY_OF_MONTH)
        val mDatePickerDialog =
            DatePickerDialog(LocalContext.current, { DatePicker, anio: Int, mes: Int, dia: Int ->
                newEventScreenViewModel.onChanged(fechaUi = "$dia/${mes + 1}/$anio", timeUi = time, tituloUi=titulo, autorUi = autor, recursoUi = recurso, descriptionUi = description)
            }, anio, mes, dia)
        val mHourPickerDialog =
            TimePickerDialog(LocalContext.current, { TimePicker, hora: Int, minuto: Int ->
                newEventScreenViewModel.onChanged(fechaUi = fecha, timeUi = "$hora:$minuto", tituloUi=titulo, autorUi = autor, recursoUi = recurso, descriptionUi = description)

            }, hora, minuto, true)
        var context = LocalContext.current
        Text(
            text = "App de gestión de eventos y citas",
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = titulo, onValueChange = {newEventScreenViewModel.onChanged(fechaUi = fecha, timeUi =time, tituloUi=it, autorUi = autor, recursoUi = recurso, descriptionUi = description) },
            label = { Text(text = "Introduce nombre del evento") }, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        OutlinedTextField(
            value = autor, onValueChange = {newEventScreenViewModel.onChanged(fechaUi = fecha, timeUi =time, tituloUi=titulo, autorUi = it, recursoUi = recurso, descriptionUi = description)},
            label = { Text(text = "Introduce tu nombre") }, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        OutlinedTextField(
            value = recurso,
            onValueChange = {newEventScreenViewModel.onChanged(fechaUi = fecha, timeUi =time, tituloUi=titulo, autorUi = autor, recursoUi = it, descriptionUi = description)},
            label = { Text(text = "Introduce recurso") }, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        OutlinedTextField(
            value = description,
            onValueChange = {newEventScreenViewModel.onChanged(fechaUi = fecha, timeUi =time, tituloUi=titulo, autorUi = autor, recursoUi = recurso, descriptionUi = it)},
            label = { Text(text = "Introduce descripción") }, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(value = fecha, onValueChange = {newEventScreenViewModel.onChanged(fechaUi = it, timeUi =time, tituloUi=titulo, autorUi = autor, recursoUi = recurso, descriptionUi = description)},
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
            OutlinedTextField(value = time, onValueChange = {newEventScreenViewModel.onChanged(fechaUi = fecha, timeUi =it, tituloUi=titulo, autorUi = autor, recursoUi = recurso, descriptionUi = description)},
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
            validarGuardarEvento(titulo, autor, recurso, description, fecha, time, newEventScreenViewModel, navController)

        }) {
            Text(text = "Guardar evento")
        }

        if(isEventoAdded){
            Toast.makeText(context, "Evento " + titulo + " creado correctamente por: " + autor, Toast.LENGTH_SHORT).show()
            newEventScreenViewModel.onListChanged(false)
        }
    }
}

fun validarGuardarEvento(title:String, autor: String, recurso: String, description: String, date: String, hour: String, newEventScreenViewModel: NewEventScreenViewModel, navController : NavController){
    var validarAutor: Boolean = false
    var validarRecursoAula : Boolean = true
    var validarCamposNulos : Boolean = true
    var rolPersonaSeleccionada: String = ""
    //Creo un evento para poder añadirlo si es correcto
    var evento: Evento = Evento(title, autor, date, hour, recurso, description)
    //recorro toda la lista de personas
    for(persona in listaTodasPersonas){
        //compruebo que el nombre del autor existe
        if(persona.nombre==autor){
            rolPersonaSeleccionada = persona.rol
            validarAutor=true
        }
    }
    //Si el recurso en minusculas es aula, su rol debe ser un profesor
    if(recurso.lowercase()==("aula")){
        //si su rol no es profesor no será valido y no se podrá añadir
        if(rolPersonaSeleccionada.lowercase()!="profesor"){
            validarRecursoAula=false
        }
    }
    //comprueba que ningún campo se ha quedado vacio
    if(title.isEmpty()||title.isBlank()||autor.isEmpty()||autor.isBlank()||
        recurso.isEmpty()||recurso.isBlank()||description.isEmpty()||description.isBlank()||
        date.isEmpty()||date.isBlank()||hour.isEmpty()||hour.isBlank()){
        validarCamposNulos=false

    }
    //Si las tres cosas hna sido validadas correctamente añado el evento a la lista, muestro el toast y le mando a la screen main
    if(validarAutor&&validarRecursoAula&&validarCamposNulos){
        allEvents.add(evento)
        newEventScreenViewModel.onListChanged(true)
        navController.navigate(AppScreens.MainScreen.route)
    }
}
