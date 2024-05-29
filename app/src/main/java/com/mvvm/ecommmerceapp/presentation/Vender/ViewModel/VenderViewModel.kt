package com.mvvm.ecommmerceapp.presentation.Vender.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.ecommmerceapp.data.Repository.ProductoRepositoryImpl
import com.mvvm.ecommmerceapp.data.Service.ProductoService
import com.mvvm.ecommmerceapp.domain.Repository.ProductoRepository
import com.mvvm.ecommmerceapp.utils.MemoryConsumption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class VenderViewModel @Inject constructor(
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