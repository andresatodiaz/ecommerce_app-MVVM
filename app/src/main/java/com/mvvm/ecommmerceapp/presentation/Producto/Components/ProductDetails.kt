package com.mvi.ecommmerceapp.presentation.Producto.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mvvm.ecommmerceapp.domain.Entities.Producto
import com.mvvm.ecommmerceapp.ui.theme.complementaryBrown
import kotlinx.coroutines.launch

@Composable
fun ProductDetails(
    vendidoPor:String,
    id:String,
    compradoPor:String,
    navController:NavController,
    producto: Producto,
    deshacer:(producto:Producto)->Unit,
    eliminar:(producto:Producto)->Unit
) {
    val coroutine = rememberCoroutineScope()
    Column(
        modifier= Modifier.fillMaxWidth(0.9f),
        horizontalAlignment = Alignment.End
    ){
        Column(modifier= Modifier.width(150.dp)){
            Button(
                enabled = vendidoPor!=id,
                onClick = {
                    if(compradoPor!=id){
                        navController.navigate("compra")
                    }else{
                        deshacer(producto)
                        navController.navigate("home")
                    }

                },
                modifier= Modifier.fillMaxWidth(),
                colors= ButtonDefaults.buttonColors(
                    containerColor = complementaryBrown,
                    contentColor = Color.Black
                )
            ) {
                if(producto.compradoPor!=id){
                    Text("Comprar")
                }else{
                    Text("Cancelar compra")
                }
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "compra",modifier= Modifier.padding(5.dp))
            }
            Spacer(modifier = Modifier.padding(5.dp))
            if(producto.vendidoPor==id){
                Button(
                    onClick = {
                        coroutine.launch {
                            eliminar(producto)
                            navController.navigate("home")
                        }
                    },
                    modifier= Modifier.fillMaxWidth(),
                    colors= ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text("Borrar")
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete",modifier= Modifier.padding(5.dp))
                }
            }
        }
    }
}