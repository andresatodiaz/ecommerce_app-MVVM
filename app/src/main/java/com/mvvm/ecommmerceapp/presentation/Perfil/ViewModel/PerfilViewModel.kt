package com.mvvm.ecommmerceapp.presentation.Perfil.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.ecommmerceapp.domain.Entities.Producto
import com.mvvm.ecommmerceapp.domain.Entities.Usuario
import com.mvvm.ecommmerceapp.data.Repository.ProductoRepositoryImpl
import com.mvvm.ecommmerceapp.data.Repository.UsuarioRepositoryImpl
import com.mvvm.ecommmerceapp.domain.Repository.ProductoRepository
import com.mvvm.ecommmerceapp.domain.Repository.UsuarioRepository
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
}