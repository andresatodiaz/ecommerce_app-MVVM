package com.mvvm.ecommmerceapp.presentation.Producto.ViewModel

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
class ProductoViewModel @Inject constructor(
    private val productoRepository: ProductoRepository,
    private val usuarioRepository: UsuarioRepository
): ViewModel() {

    val vendedor = mutableStateOf(Usuario())
    val usuario = mutableStateOf(Usuario())
    fun eliminar(producto: Producto){
        viewModelScope.launch {
            productoRepository.borrarProducto(producto)
        }
    }
    fun deshacer(producto: Producto){
        viewModelScope.launch {
            productoRepository.comprarProducto(producto)
        }
    }

    fun getData(id:String){
        viewModelScope.launch {
            vendedor.value =usuarioRepository.getVendedor(id)!!
            usuario.value = usuarioRepository.getMyUser()!!
        }
    }

}