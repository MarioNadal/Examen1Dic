package com.iessanalberto.dam2.examen1dic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class BorrarEventScreenViewModel {
    private val _fecha = MutableLiveData<String>()
    val fecha: LiveData<String> = _fecha

    private val _time = MutableLiveData<String>()
    val time: LiveData<String> = _time

    private val _autor = MutableLiveData<String>()
    val autor: LiveData<String> = _autor

    private val _isEventAdded = MutableLiveData<Boolean>()
    val isEventAdded: LiveData<Boolean> = _isEventAdded


    fun onChanged(fechaUi: String, timeUi:String, autorUi: String){
        _fecha.value = fechaUi
        _time.value = timeUi
        _autor.value = autorUi
    }
    fun onListChanged(isEventAddedUi: Boolean){
        _isEventAdded.value = isEventAddedUi
    }

}