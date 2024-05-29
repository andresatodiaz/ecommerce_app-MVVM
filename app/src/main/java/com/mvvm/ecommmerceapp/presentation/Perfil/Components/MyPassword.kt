package com.mvi.ecommmerceapp.presentation.Perfil.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mvvm.ecommmerceapp.domain.Entities.Usuario

@Composable
fun MyPassword(
    usuario: Usuario
) {
    Column(modifier= Modifier.fillMaxWidth(0.9f)) {
        Text("Contrasena", fontWeight = FontWeight.Bold)
        Text(usuario.contrasena)
    }
    Spacer(Modifier.padding(10.dp))
}