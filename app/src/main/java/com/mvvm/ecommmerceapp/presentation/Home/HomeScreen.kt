package com.mvvm.ecommmerceapp.presentation.Home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mvvm.ecommmerceapp.MainApplication
import com.mvvm.ecommmerceapp.domain.Entities.Producto
import com.mvvm.ecommmerceapp.presentation.Home.ViewModel.HomeViewModel
import com.mvvm.ecommmerceapp.ui.theme.cardBrown
import com.mvvm.ecommmerceapp.ui.theme.complementaryBrown
import com.mvvm.ecommmerceapp.ui.theme.mainBrown
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mvi.ecommmerceapp.presentation.Home.Components.Grid
import com.mvi.ecommmerceapp.presentation.Home.Components.HomeFloatingButton
import com.mvi.ecommmerceapp.presentation.Home.Components.HomeSwipeIndicator

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
    val swipeRefreshState  = rememberSwipeRefreshState(isRefreshing = homeViewModel.refreshing.value)

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