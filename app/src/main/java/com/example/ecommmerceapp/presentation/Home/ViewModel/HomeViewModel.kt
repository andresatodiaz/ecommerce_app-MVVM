package com.example.ecommmerceapp.presentation.Home.ViewModel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommmerceapp.MainApplication
import com.example.ecommmerceapp.data.Entities.Producto
import com.example.ecommmerceapp.data.Entities.Usuario
import com.example.ecommmerceapp.data.Repository.ProductoRepository
import com.example.ecommmerceapp.data.Repository.UsuarioRepository
import com.example.ecommmerceapp.data.Service.ProductoService
import com.example.ecommmerceapp.data.Service.UsuarioService
import com.example.ecommmerceapp.utils.MemoryConsumption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productoService: ProductoService,
    private val usuarioService: UsuarioService,
    private val productoRepository: ProductoRepository,
    private val usuarioRepository: UsuarioRepository
): ViewModel() {

    val isLoading = mutableStateOf(false)
    val vendedorProducto = mutableStateOf(Usuario())
    val loadingVendedor= mutableStateOf(false)
    val vendedorEmpty = mutableStateOf(false)
    val misProductos= mutableStateOf(emptyList<Producto>())

    val refreshing = mutableStateOf(true)
    val usuario = mutableStateOf(Usuario())
    val productos = mutableStateOf(emptyList<Producto>())

    @RequiresApi(Build.VERSION_CODES.O)
    fun getData(){
        refreshing.value=true
        viewModelScope.launch {
            val startTime = LocalTime.now()
            usuario.value = usuarioRepository.getMyUser()
            productos.value= productoRepository.getProductos()!!
            refreshing.value=false
            Log.i("comp-ExecutionTime", Duration.between(startTime,LocalTime.now()).toMillis().toString())
            Log.i("comp-MemoryConsump", MemoryConsumption().getUsedMemorySize().toString())
        }
    }

    fun getProductos(){
        viewModelScope.launch {
            isLoading.value=true
           if( productoService.getProductos() !=null){
               productos.value= productoService.getProductos()!!.sortedBy {producto->
                   producto.precio.toInt()
               }
           }
            isLoading.value=false
        }
    }



    fun getMisProductos(id:String){
        viewModelScope.launch {
            isLoading.value=true
            if( productoService.getProductos() !=null){
                misProductos.value= productoService.getProductos()!!.filter {producto->
                    producto.vendidoPor==id
                }
            }
            Log.i("home",misProductos.value.toString())
            isLoading.value=false
        }
    }

    fun comprarProducto(producto: Producto){
        viewModelScope.launch {
            val sp = MainApplication.applicationContext().getSharedPreferences(
                "preferences",
                Context.MODE_PRIVATE
            )

            productoService.comprarProducto(
                Producto(
                    producto.id,
                    producto.titulo,
                    producto.descripcion,
                    producto.precio,
                    producto.estado,
                    sp.getString("LOGGED_ID",""),
                    producto.vendidoPor
                )
            )
        }
    }

    fun borrarProducto(producto:Producto){
        viewModelScope.launch {
            productoService.borrarProducto(producto)
            getProductos()
        }
    }

    fun getVendedor(id:String){
        viewModelScope.launch {
            if(usuarioService.getVendedor(id)!=null){
                loadingVendedor.value=true
                if(usuarioService.getVendedor(id)==null){
                    vendedorEmpty.value=true
                }else{
                    vendedorProducto.value=usuarioService.getVendedor(id)!!
                }
                loadingVendedor.value=false
            }
        }
    }

    fun testProductos(){
        productos.value= listOf(
            Producto(
                "1",
                "prod1",
                "prod1",
                "10",
                10
            ),
            Producto(
                "2",
                "prod2",
                "prod2",
                "20",
                10
            ),
            Producto(
                "3",
                "prod3",
                "prod3",
                "30",
                10
            ),
            Producto(
                "4",
                "prod4",
                "prod4",
                "40",
                10
            )
        )
    }
}