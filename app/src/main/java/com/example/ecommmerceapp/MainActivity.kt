package com.example.ecommmerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ecommmerceapp.presentation.MainNavigation.MainNavigationScreen
import com.example.ecommmerceapp.presentation.QrScanner.QrScannerView
import com.example.ecommmerceapp.ui.theme.ECommmerceAppTheme
import com.example.ecommmerceapp.ui.theme.mainBrown
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
@ExperimentalGetImage
@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val flagKillActivity= mutableStateOf(false)
        super.onCreate(savedInstanceState)
        setContent {
            ECommmerceAppTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val uiController = rememberSystemUiController()
                    uiController.setSystemBarsColor(mainBrown)
                    uiController.setNavigationBarColor(mainBrown)
                    val showQRScanner = remember{mutableStateOf(false)}
                    MainNavigationScreen(
                        finishActivity = finishActivity(flagKillActivity.value),
                        flagKillActivity = flagKillActivity,
                        showQRScanner = showQRScanner
                    )

                }
            }
        }
    }
    fun finishActivity(flagKillActivity : Boolean){
        if(flagKillActivity){
            finish()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ECommmerceAppTheme {
        Greeting("Android")
    }
}