package com.mvvm.ecommmerceapp.domain.Repository

import com.mvvm.ecommmerceapp.domain.Entities.Producto
import com.mvvm.ecommmerceapp.domain.Entities.Usuario

interface UsuarioRepository {
    suspend fun getVendedor(id:String): Usuario?
    suspend fun login(correo:String,contrasena:String,goToMain:()->Unit)
    suspend fun register(correo: String, contrasena: String, nombres: String, apellidos:String)
    suspend fun getMyUser(): Usuario
}