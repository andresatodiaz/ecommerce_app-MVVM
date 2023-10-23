package com.example.ecommmerceapp.presentation.Vender.ViewModel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommmerceapp.MainApplication
import com.example.ecommmerceapp.data.Entities.Producto
import com.example.ecommmerceapp.data.Repository.ProductoRepository
import com.example.ecommmerceapp.data.Service.ProductoService
import com.example.ecommmerceapp.utils.MemoryConsumption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class VenderViewModel @Inject constructor(
    private val productoService: ProductoService,
    private val productoRepository: ProductoRepository
): ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    fun agregarProducto(titulo:String, descripcion:String, precio:String, estado:Int){
        viewModelScope.launch {
            val startTime = LocalTime.now()
            productoRepository.agregarProducto(titulo,descripcion,precio,estado)
            Log.i("comp-ExecutionTime", Duration.between(startTime,LocalTime.now()).toMillis().toString())
            Log.i("comp-MemoryConsump", MemoryConsumption().getUsedMemorySize().toString())
        }
    }
}