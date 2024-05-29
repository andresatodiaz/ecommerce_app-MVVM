package com.mvi.ecommmerceapp.presentation.Vender.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mvvm.ecommmerceapp.ui.theme.colorMatrix

@Composable
fun VenderBanner(){
    AsyncImage(model = "https://picsum.photos/500/500/?blur=5", contentDescription = "background",
        modifier= Modifier
            .fillMaxWidth()
            .height(120.dp),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
    )
    Text("Vender producto",modifier= Modifier.padding(top=50.dp), fontWeight = FontWeight.Black, color = Color.White, fontSize = 20.sp)
}