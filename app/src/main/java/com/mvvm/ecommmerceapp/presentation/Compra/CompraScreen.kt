package com.mvvm.ecommmerceapp.presentation.Compra

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.mvi.ecommmerceapp.presentation.Components.CompraBanner
import com.mvi.ecommmerceapp.presentation.Components.CompraEsp
import com.mvi.ecommmerceapp.presentation.Components.CompraFloatingButton
import com.mvi.ecommmerceapp.presentation.Components.CompraSalePersonEsp
import com.mvi.ecommmerceapp.presentation.Components.ConfirmacionCard
import com.mvi.ecommmerceapp.presentation.Components.DescuentoCard
import com.mvi.ecommmerceapp.presentation.Components.ProductoCard
import com.mvi.ecommmerceapp.presentation.Components.SignatureDialog
import com.mvi.ecommmerceapp.presentation.Components.VendedorCard
import com.mvvm.ecommmerceapp.domain.Entities.Producto
import com.mvvm.ecommmerceapp.presentation.Compra.ViewModel.CompraViewModel
import com.mvvm.ecommmerceapp.presentation.QrScanner.ViewModel.QrScannerViewModel
import com.mvvm.ecommmerceapp.presentation.Signature.DigitalInkViewModel
import com.mvvm.ecommmerceapp.presentation.Signature.use
import com.mvvm.ecommmerceapp.utils.MemoryConsumption
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompraScreen(
    photo: String,
    producto: Producto,
    navController: NavController,
    compraViewModel: CompraViewModel,
    qrScannerViewModel: QrScannerViewModel,
    digitalInkViewModel: DigitalInkViewModel
) {
    val showSignature = remember{ mutableStateOf(false) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val (state, event) = use(digitalInkViewModel, DigitalInkViewModel.State())

    LaunchedEffect(key1 = true ){
        event(DigitalInkViewModel.Event.ResetText)
        qrScannerViewModel.getExecutionTime()
        qrScannerViewModel.getMemoryUsage()
    }

    DisposableEffect(Unit) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP)
                event(DigitalInkViewModel.Event.OnStop)
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose { lifecycleOwner.lifecycle.removeObserver(lifecycleObserver) }
    }

    val discountLink = remember{ mutableStateOf("") }


    LaunchedEffect(key1 = true){
        compraViewModel.getVendedor(producto.vendidoPor!!)
        discountLink.value=qrScannerViewModel.qrLink.value

    }

    Scaffold(
        floatingActionButton = {
            CompraFloatingButton {
                compraViewModel.comprar(producto)
                navController.navigate("home")
            }
        }
    ) {
        Box(Modifier.fillMaxSize()){
            LazyColumn(
                modifier=Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(top=20.dp, bottom = 100.dp)
            ){
                item{
                    CompraBanner()
                }
                item{
                    CompraSalePersonEsp()
                }
                item{
                    VendedorCard(
                        nombre = compraViewModel.vendedor.value.nombre ,
                        apellido = compraViewModel.vendedor.value.apellido ,
                        correo = compraViewModel.vendedor.value.correo
                    )
                }
                item{
                    CompraEsp()
                }
                item{
                    ProductoCard(
                        photo = photo,
                        producto = producto
                    )
                }
                item{
                    Column(
                        modifier= Modifier
                            .fillMaxWidth(0.9f)
                            .padding(10.dp)
                    ){
                        DescuentoCard(
                            discountLink = discountLink,
                        ){
                            navController.navigate("qrscanner")
                            navController.navigate("qrscanner")
                            qrScannerViewModel.startEx.value=LocalTime.now()
                            qrScannerViewModel.startRam.value= MemoryConsumption().getUsedMemorySize()
                        }
                        ConfirmacionCard(
                            finalText=state.finalText,
                            showSignature = showSignature
                        )
                    }


                }
            }
        }
    }
    if(showSignature.value){
        SignatureDialog(
            showSignature=showSignature,
            digitalInkState = digitalInkViewModel.state.collectAsState().value,
            pointerEvent = event
        )
    }
}