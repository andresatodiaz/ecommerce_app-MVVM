package com.mvi.ecommmerceapp.presentation.Components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mvvm.ecommmerceapp.ui.theme.cardBrown
import com.mvvm.ecommmerceapp.ui.theme.mainBrown


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DescuentoCard(
    discountLink:MutableState<String>,
    qrEvent: ()->Unit
) {
    Text("Obtener descuento",modifier=Modifier, fontWeight = FontWeight.Black)
    Text("Escanear QR único")


    if(discountLink.value!=""){
        Spacer(modifier = Modifier.padding(10.dp))
        Box(
            modifier= Modifier.background(cardBrown, RoundedCornerShape(20.dp))
        ){
            Text("Link de descuento " + discountLink.value,modifier= Modifier
                .padding(10.dp)
                .align(
                    Alignment.Center
                ))
        }

    }
    Spacer(modifier = Modifier.padding(10.dp))
    Button(
    onClick = {
        qrEvent()
    },
    modifier= Modifier.fillMaxWidth(),
    colors= ButtonDefaults.buttonColors(
    containerColor = mainBrown,
    contentColor = Color.White
    )
    ) {
        if(discountLink.value!=""){
            Text("Volver a escanear")
        }else{
            Text("Escanear")
        }

    }
    Spacer(modifier=Modifier.padding(10.dp))
}