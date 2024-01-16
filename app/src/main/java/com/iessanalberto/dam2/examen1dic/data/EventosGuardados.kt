package com.iessanalberto.dam2.examen1dic.data

import com.iessanalberto.dam2.examen1dic.models.Evento

val evento1: Evento = Evento(
    "Evento 1",
    "Autor 1",
    "01/01/2021",
    "10:00",
    "https://www.google.com",
    "Descripción del evento 1"
)
val evento2: Evento = Evento(
    "Evento 2",
    "Autor 2",
    "02/02/2021",
    "11:00",
    "https://www.google.com",
    "Descripción del evento 2"
)
val evento3: Evento = Evento(
    "Evento 3",
    "Autor 3",
    "03/03/2021",
    "12:00",
    "https://www.google.com",
    "Descripción del evento 3"
)
val evento4: Evento = Evento(
    "Evento 4",
    "Autor 4",
    "04/04/2021",
    "13:00",
    "https://www.google.com",
    "Descripción del evento 4"
)
val allEvents = mutableListOf<Evento>(evento1, evento2, evento3, evento4)