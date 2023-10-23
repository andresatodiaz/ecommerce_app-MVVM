package com.example.ecommmerceapp.presentation.Login.ViewModel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommmerceapp.MainApplication
import com.example.ecommmerceapp.data.Entities.Usuario
import com.example.ecommmerceapp.data.Repository.UsuarioRepository
import com.example.ecommmerceapp.data.Service.UsuarioService
import com.example.ecommmerceapp.utils.MemoryConsumption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usuarioService: UsuarioService,
    private val usuarioRepository: UsuarioRepository
): ViewModel() {
    val loggedUser= mutableStateOf(Usuario())
    val loginSuccess = mutableStateOf(false)
    val loginLoading = mutableStateOf(true)
    fun showUsers(){
        viewModelScope.launch {
            usuarioService.showUsers()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun login(correo: String, contrasena: String, goToMain: ()->Unit){
        viewModelScope.launch {
            val startTime = LocalTime.now()
            usuarioRepository.login(correo,contrasena,goToMain)
            Log.i("comp-ExecutionTime",Duration.between(startTime,LocalTime.now()).toMillis().toString())
            Log.i("comp-MemoryConsump",MemoryConsumption().getUsedMemorySize().toString())
        }
    }

    fun agregarUsuario(correo: String, contrasena: String, nombres: String, apellidos:String){
        viewModelScope.launch {
            usuarioRepository.register(correo,contrasena,nombres,apellidos)
        }
    }
}