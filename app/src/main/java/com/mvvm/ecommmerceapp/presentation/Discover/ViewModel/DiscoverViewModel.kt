package com.mvvm.ecommmerceapp.presentation.Discover.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.ecommmerceapp.domain.Entities.Producto
import com.mvvm.ecommmerceapp.domain.Entities.Usuario
import com.mvvm.ecommmerceapp.domain.Repository.ProductoRepository
import com.mvvm.ecommmerceapp.domain.Repository.UsuarioRepository
import com.mvvm.ecommmerceapp.utils.MemoryConsumption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository,
    private val productoRepository: ProductoRepository
): ViewModel() {
    val refreshing = mutableStateOf(true)
    val usuario = mutableStateOf(Usuario())
    val productos = mutableStateOf(emptyList<Producto>())

    @RequiresApi(Build.VERSION_CODES.O)
    fun getData() {
        refreshing.value = true
        viewModelScope.launch {
            val startTime = LocalTime.now()
            usuario.value = usuarioRepository.getMyUser()
            productos.value = productoRepository.getProductos()!!
            refreshing.value = false
            Log.i(
                "comp-ExecutionTime",
                Duration.between(startTime, LocalTime.now()).toMillis().toString()
            )
            Log.i("comp-MemoryConsump", MemoryConsumption().getUsedMemorySize().toString())
        }
    }
}