package com.example.ecommmerceapp.presentation.QrScanner

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ecommmerceapp.presentation.QrScanner.ViewModel.QrScannerViewModel
import com.example.ecommmerceapp.ui.theme.cardBrown
import com.example.ecommmerceapp.ui.theme.mainBrown
import com.example.ecommmerceapp.utils.MemoryConsumption
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalGetImage
@Composable
fun QrScannerScreen(
    navController: NavController,
    qrScannerViewModel: QrScannerViewModel
) {
    val data = remember{mutableStateOf("")}


    LaunchedEffect(key1 = data.value ){
        if(data.value!=""){
            qrScannerViewModel.qrLink.value=data.value
            qrScannerViewModel.endEx.value= LocalTime.now()
            qrScannerViewModel.endRam.value= MemoryConsumption().getUsedMemorySize()
            delay(2000)
            navController.navigate("compra")
        }
    }

    Box(
        modifier=Modifier.fillMaxSize()
    ) {
        QrScannerView(
            Modifier
                .size(300.dp, 300.dp)
                .align(Alignment.Center)
                .padding(top = 50.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(BorderStroke(5.dp, mainBrown), RoundedCornerShape(20.dp))
            ,data)
        Text("Escaner de código de barras",modifier= Modifier
            .align(Alignment.TopCenter)
            .padding(top = 50.dp), fontWeight = FontWeight.Black, fontSize = 20.sp)
        Box(modifier= Modifier
            .align(Alignment.TopCenter)
            .padding(top = 100.dp)
            .fillMaxWidth(0.7f)
            .background(
                cardBrown, RoundedCornerShape(20.dp)
            )) {
            if(data.value!=""){
                Text(data.value,modifier= Modifier.padding(10.dp))
            }else{
                Text("Escaneando...",modifier=Modifier.padding(10.dp))
            }

        }

    }
}