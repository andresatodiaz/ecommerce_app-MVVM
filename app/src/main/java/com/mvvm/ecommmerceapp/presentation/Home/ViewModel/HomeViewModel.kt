package com.mvvm.ecommmerceapp.presentation.Home.ViewModel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.ecommmerceapp.MainApplication
import com.mvvm.ecommmerceapp.domain.Entities.Producto
import com.mvvm.ecommmerceapp.domain.Entities.Usuario
import com.mvvm.ecommmerceapp.data.Repository.ProductoRepositoryImpl
import com.mvvm.ecommmerceapp.data.Repository.UsuarioRepositoryImpl
import com.mvvm.ecommmerceapp.data.Service.ProductoService
import com.mvvm.ecommmerceapp.data.Service.UsuarioService
import com.mvvm.ecommmerceapp.domain.Repository.ProductoRepository
import com.mvvm.ecommmerceapp.domain.Repository.UsuarioRepository
import com.mvvm.ecommmerceapp.utils.MemoryConsumption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productoRepository: ProductoRepository,
    private val usuarioRepository: UsuarioRepository
): ViewModel() {

    val refreshing = mutableStateOf(true)
    val usuario = mutableStateOf(Usuario())
    val productos = mutableStateOf(emptyList<Producto>())
    val token = mutableStateOf("")

    @RequiresApi(Build.VERSION_CODES.O)
    fun getData(){
        refreshing.value=true
        viewModelScope.launch {
            val startTime = LocalTime.now()
            usuario.value = usuarioRepository.getMyUser()
            productos.value= productoRepository.getProductos()!!
            getToken()
            refreshing.value=false
            Log.i("comp-ExecutionTime", Duration.between(startTime,LocalTime.now()).toMillis().toString())
            Log.i("comp-MemoryConsump", MemoryConsumption().getUsedMemorySize().toString())
        }
    }
    fun getToken(){
        val sp = MainApplication.applicationContext().getSharedPreferences(
            "preferences",
            Context.MODE_PRIVATE
        )
        token.value=sp.getString("LOGGED_ID","")!!
    }
}