package com.iessanalberto.dam2.examen1dic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NewEventScreenViewModel {
    private val _fecha = MutableLiveData<String>()
    val fecha: LiveData<String> = _fecha

    private val _time = MutableLiveData<String>()
    val time: LiveData<String> = _time

    private val _titulo = MutableLiveData<String>()
    val titulo: LiveData<String> = _titulo

    private val _autor = MutableLiveData<String>()
    val autor: LiveData<String> = _autor

    private val _recurso = MutableLiveData<String>()
    val recurso: LiveData<String> = _recurso

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _isEventAdded = MutableLiveData<Boolean>()
    val isEventAdded: LiveData<Boolean> = _isEventAdded

    fun onChanged(fechaUi: String, timeUi:String, tituloUi: String, autorUi: String, recursoUi: String, descriptionUi: String){
        _fecha.value = fechaUi
        _time.value = timeUi
        _titulo.value = tituloUi
        _autor.value = autorUi
        _recurso.value = recursoUi
        _description.value = descriptionUi
    }

    fun onListChanged(isEventAddedUi: Boolean){
        _isEventAdded.value = isEventAddedUi
    }

}