package com.mvvm.ecommmerceapp.Navigation.BottomNav

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mvvm.ecommmerceapp.Navigation.BottomNav.Components.NavItem
import com.mvvm.ecommmerceapp.ui.theme.secondaryBrown

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val item = listOf(
        NavItem.Home,
        NavItem.Discover,
        NavItem.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp),
        containerColor = secondaryBrown
    ) {
        Row(
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            item.forEach {
                    item->
                Column(modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable {
                        navController.navigate(item.route)
                    },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "icon",
                        modifier=Modifier.size(25.dp)
                    )
                    if(currentRoute==item.route){
                        Text(
                            text=item.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

    }
}