package com.mvvm.ecommmerceapp.presentation.QrScanner.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mvvm.ecommmerceapp.utils.MemoryConsumption
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Duration
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class QrScannerViewModel @Inject constructor(

):ViewModel() {
    val qrLink = mutableStateOf("")

    @RequiresApi(Build.VERSION_CODES.O)
    val startEx = mutableStateOf(LocalTime.now())
    @RequiresApi(Build.VERSION_CODES.O)
    val endEx = mutableStateOf(LocalTime.now())

    val startRam = mutableStateOf(0L)
    val endRam = mutableStateOf(0L)


    @RequiresApi(Build.VERSION_CODES.O)
    fun getExecutionTime(){
        Log.i("comp-ExecutionTime", Duration.between(startEx.value,endEx.value).toMillis().toString())
        startEx.value= LocalTime.now()
        endEx.value= LocalTime.now()
    }

    fun getMemoryUsage(){
        Log.i("comp-MemoryConsump", MemoryConsumption().getUsedMemorySize().toString())
    }
}
