package com.mvvm.ecommmerceapp.Navigation.BottomNav.Components

import com.mvvm.ecommmerceapp.R

sealed class NavItem(
    val route : String,
    val title : String,
    val icon : Int
) {
    object Home: NavItem(
        route="home",
        title = "Inicio",
        icon= R.drawable.home
    )
    object Discover: NavItem(
        route="discover",
        title = "Descubre",
        icon= R.drawable.search
    )
    object Profile: NavItem(
        route="profile",
        title = "Perfil",
        icon= R.drawable.user
    )
}