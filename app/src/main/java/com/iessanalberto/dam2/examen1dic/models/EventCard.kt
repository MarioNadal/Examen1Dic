package com.iessanalberto.dam2.examen1dic.models


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EventCard(evento: Evento){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(10.dp)
    ) {
        Text(text = evento.title,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(3.dp))
        Text(text = "Fecha: ${evento.date}" )
        Text(text = "Hora: ${evento.hour}")
        Text(text = "Author: ${evento.author}")
        Text(text = "Recurso: ${evento.recurso}")
        Text(text = "Descripción: ${evento.description}")
    }
}