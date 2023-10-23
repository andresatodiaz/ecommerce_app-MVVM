package com.example.ecommmerceapp.presentation.Perfil.ViewModel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommmerceapp.MainApplication
import com.example.ecommmerceapp.data.Entities.Producto
import com.example.ecommmerceapp.data.Entities.Usuario
import com.example.ecommmerceapp.data.Repository.ProductoRepository
import com.example.ecommmerceapp.data.Repository.UsuarioRepository
import com.example.ecommmerceapp.data.Service.UsuarioService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val productoRepository: ProductoRepository,
    private val usuarioRepository: UsuarioRepository
):ViewModel() {
    val myUser = mutableStateOf(Usuario())

    val usuario = mutableStateOf(Usuario())
    val productos = mutableStateOf(emptyList<Producto>())
    val refreshing = mutableStateOf(true)

    fun getData(){
        refreshing.value=true
        viewModelScope.launch {
            usuario.value = usuarioRepository.getMyUser()
            productos.value = productoRepository.getMisProductos(usuario.value.id)!!
            refreshing.value=false
        }
    }

    fun testUser(){
        myUser.value=Usuario(
            id="c2816877-a027-4527-852c-20c2d33e2895",
            nombre="test1",
            apellido="test1",
            correo= "demo@gmail.com",
            contrasena ="123"
        )
    }
}