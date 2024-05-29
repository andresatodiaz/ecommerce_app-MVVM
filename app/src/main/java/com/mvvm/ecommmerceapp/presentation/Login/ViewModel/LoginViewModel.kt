package com.mvvm.ecommmerceapp.presentation.Login.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.ecommmerceapp.domain.Entities.Usuario
import com.mvvm.ecommmerceapp.data.Repository.UsuarioRepositoryImpl
import com.mvvm.ecommmerceapp.data.Service.UsuarioService
import com.mvvm.ecommmerceapp.domain.Repository.UsuarioRepository
import com.mvvm.ecommmerceapp.utils.MemoryConsumption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository
): ViewModel() {

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