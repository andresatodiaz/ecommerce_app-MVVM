package com.mvi.ecommmerceapp.presentation.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CompraEsp(){
    Column(
        modifier= Modifier.fillMaxWidth(0.9f)
    ){
        Text("Datos del producto",modifier= Modifier.padding(10.dp), fontWeight = FontWeight.Black)
    }
}