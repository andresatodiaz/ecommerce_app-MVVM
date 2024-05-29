package com.mvvm.ecommmerceapp.presentation.Home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mvvm.ecommmerceapp.domain.Entities.Producto
import com.mvvm.ecommmerceapp.presentation.Home.ViewModel.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mvi.ecommmerceapp.presentation.Home.Components.Grid
import com.mvi.ecommmerceapp.presentation.Home.Components.HomeFloatingButton
import com.mvi.ecommmerceapp.presentation.Home.Components.HomeSwipeIndicator

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    selectedProducto:MutableState<Producto>,
    selectedProductoUrl:MutableState<String>,
    homeViewModel: HomeViewModel,
) {
    val id = remember{mutableStateOf("")}
    val refreshing= homeViewModel.refreshing.value
    val swipeRefreshState  = rememberSwipeRefreshState(isRefreshing = refreshing)

    LaunchedEffect(key1 = true){
        homeViewModel.getData()
    }

    LaunchedEffect(key1 = homeViewModel.token){
        id.value=homeViewModel.token.value
    }


    Scaffold(
        floatingActionButton = {
            HomeFloatingButton(navController = navController)
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ){
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    homeViewModel.getData()
                },
                indicator = { state, trigger ->
                    HomeSwipeIndicator(state,trigger)
                }
            ) {
                Grid(
                    usuario = homeViewModel.usuario.value,
                    refreshing = homeViewModel.refreshing.value,
                    productos = homeViewModel.productos.value,
                    id=id.value,
                    navController=navController,
                    selectedProducto=selectedProducto,
                    selectedProductoUrl=selectedProductoUrl,
                )
            }

        }
    }
}