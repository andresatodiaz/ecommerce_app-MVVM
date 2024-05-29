package com.mvvm.ecommmerceapp.Navigation

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mvvm.ecommmerceapp.domain.Entities.Producto
import com.mvvm.ecommmerceapp.presentation.Compra.CompraScreen
import com.mvvm.ecommmerceapp.presentation.Compra.ViewModel.CompraViewModel
import com.mvvm.ecommmerceapp.presentation.Vender.CrearProductoScreen
import com.mvvm.ecommmerceapp.presentation.Vender.ViewModel.VenderViewModel
import com.mvvm.ecommmerceapp.presentation.Discover.DiscoverScreen
import com.mvvm.ecommmerceapp.presentation.Discover.ViewModel.DiscoverViewModel
import com.mvvm.ecommmerceapp.presentation.Home.HomeScreen
import com.mvvm.ecommmerceapp.presentation.Home.ViewModel.HomeViewModel
import com.mvvm.ecommmerceapp.presentation.Perfil.PerfilScreen
import com.mvvm.ecommmerceapp.presentation.Perfil.ViewModel.PerfilViewModel
import com.mvvm.ecommmerceapp.presentation.Producto.ProductoScreen
import com.mvvm.ecommmerceapp.presentation.Producto.ViewModel.ProductoViewModel
import com.mvvm.ecommmerceapp.presentation.QrScanner.QrScannerScreen
import com.mvvm.ecommmerceapp.presentation.QrScanner.ViewModel.QrScannerViewModel
import com.mvvm.ecommmerceapp.presentation.Signature.DigitalInkViewModel
import com.mvvm.ecommmerceapp.presentation.Signature.DigitalInkViewModelImpl

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalGetImage
@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    finishActivity : Unit,
    flagKillActivity : MutableState<Boolean>,
    homeViewModel: HomeViewModel = hiltViewModel(),
    perfilViewModel: PerfilViewModel = hiltViewModel(),
    venderViewModel: VenderViewModel = hiltViewModel(),
    compraViewModel: CompraViewModel = hiltViewModel(),
    productoViewModel: ProductoViewModel = hiltViewModel(),
    qrScannerViewModel: QrScannerViewModel= hiltViewModel(),
    discoverViewModel: DiscoverViewModel= hiltViewModel(),
    digitalInkViewModel: DigitalInkViewModel = hiltViewModel<DigitalInkViewModelImpl>()
) {
    val selectedProducto = remember{mutableStateOf(Producto())}
    val selectedProductoUrl = remember{ mutableStateOf("") }
    NavHost(
        navController = navController,
        startDestination = "home",

        ) {
        composable("home"){
            qrScannerViewModel.qrLink.value=""
            HomeScreen(
                navController=navController,
                selectedProducto=selectedProducto,
                selectedProductoUrl=selectedProductoUrl,
                homeViewModel=homeViewModel
            )
        }
        composable("discover"){
            DiscoverScreen(discoverViewModel)
        }
        composable("profile"){
            PerfilScreen(
                finishActivity=finishActivity,
                flagKillActivity=flagKillActivity,
                perfilViewModel=perfilViewModel,
                navController=navController,
                selectedProducto=selectedProducto,
                selectedProductoUrl=selectedProductoUrl
            )
        }
        composable("vender"){
            CrearProductoScreen(
                navController=navController,
                venderViewModel=venderViewModel,
            )
        }
        composable("producto"){
            qrScannerViewModel.qrLink.value=""
            BackHandler {
                navController.navigate("home")
            }
            ProductoScreen(
                photo = selectedProductoUrl.value,
                producto = selectedProducto.value,
                navController=navController,
                productoViewModel = productoViewModel
            )
        }
        composable("compra"){
            BackHandler {
                navController.navigate("home")
            }
            CompraScreen(
                photo = selectedProductoUrl.value,
                producto = selectedProducto.value,
                navController=navController,
                compraViewModel=compraViewModel,
                qrScannerViewModel=qrScannerViewModel,
                digitalInkViewModel=digitalInkViewModel
            )

        }
        composable("qrscanner"){
            BackHandler {
                navController.navigate("producto")
            }
            QrScannerScreen(
                navController=navController,
                qrScannerViewModel=qrScannerViewModel
            )
        }
    }

}